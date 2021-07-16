package com.crawler.app.Logic;

import com.crawler.app.Config.Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import static com.crawler.app.Config.Strings.*;

public final class RobotsTxt {

    private final String url;
    private String line;

    private String protocol;
    private String authority;

    private ArrayList<String> arrayDisallowFiltered;
    private ArrayList<String> arrayAllowFiltered;

    private int position;  /** position "User-agent: *" */

    public RobotsTxt(String pUrl) {
        this.url = getUrlRobots(pUrl);
        if (this.url != null) {
            this.line = null;

            this.arrayDisallowFiltered = new ArrayList<>();
            this.arrayAllowFiltered = new ArrayList<>();
            this.filterRobotsTxt();
        } else {
            JOptionPane.showMessageDialog(null, INVALID_URL_STRING, ERROR_STRING,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Preso in input un url ritorna il path al "robots.txt"
     *
     * @param pUrl non completo
     * @return "robots.txt"
     */
    private String getUrlRobots(String pUrl) {
        try {
            URL robotsUrl = new URL(pUrl);

            this.protocol = robotsUrl.getProtocol();
            this.authority = robotsUrl.getAuthority();

            return robotsUrl.getProtocol().concat(URL_UTILS).concat(robotsUrl.getAuthority()).concat(ROBOTS);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    private void filterRobotsTxt() {
        ArrayList<String> arrayRobots = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(this.url).openStream()))) {
            /**
             * Mette tutto il robots.txt nell'array arrayRobots
             */
            while ((this.line = in.readLine()) != null) {
                arrayRobots.add(this.line);
            }

            /**
             * Se c'è un User-agent: * nel file "robots.txt", ci interessa
             * prendere i sottostanti Disallow e Allow che finiranno negli array
             * arrayDisallowFiltered e arrayAllowFiltered
             */
            if (checkUserAgentStar(arrayRobots)) {
                while (this.position < arrayRobots.size() && arrayRobots.get(this.position).startsWith(ALLOW)
                        || arrayRobots.get(this.position).startsWith(DISALLOW)) {
                    if (arrayRobots.get(this.position).startsWith(Strings.DISALLOW)) {
                        this.arrayDisallowFiltered.add(getCompleteUrl(arrayRobots.get(this.position)
                                .substring(9)).replace(" ", ""));
                    }
                    else if (arrayRobots.get(this.position).startsWith(Strings.ALLOW)){
                        this.arrayAllowFiltered.add(getCompleteUrl(arrayRobots.get(this.position)
                                .substring(6)).replace(" ", ""));
                    }

                    this.position++;
                }
            }
        } catch (IOException e) {
        }
    }

    /**
     * Dice se all'interno del robots.txt di un sito è presente "User-agent: *"
     * in quanto è l'unica direttiva che il crawler deve 'ascoltare'
     *
     * @param array contenente il file robots.txt completo
     *              position è la riga successiva a "User-agent: *",
     *              ci interessa per prendere i sottostanti Allow e Disallow
     * @return true se è presente "User-agent: *"
     */
    private boolean checkUserAgentStar(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).startsWith(USER_AGENT_STAR)) {
                this.position = i + 1;
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo pubblico: verifica se un url è Allow o Disallow
     *
     * @param url da controllare
     * @return true se è possibile fare Crawling sull'url, false altrimenti
     */
    public boolean isAllow(String url) {
        for(int i = 0; i < this.getAllowsFiltered().size(); i ++){
            if(url.equals(this.getAllowsFiltered().get(i))){
                return true;
            }
            else if(url.contains(this.getAllowsFiltered().get(i))){
                for(String urlsDisallow : this.getDisallowsFiltered()){
                    if(url.equals(urlsDisallow)){
                        return false;
                    }
                    else
                        return true;
                }
            }
        }

        for(int i = 0; i < this.getDisallowsFiltered().size(); i ++){
            if(url.equals(this.getDisallowsFiltered().get(i))){
                return false;
            }
            else if(url.contains(this.getDisallowsFiltered().get(i))){
                for(String urlsAllow : this.getAllowsFiltered()){
                    if(url.equals(urlsAllow)){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Serve a completare gli url incompleti presi dal "robots.txt"
     *
     * @param notCompletedUrl url da completare
     * @return url completo
     */
    private String getCompleteUrl(String notCompletedUrl) {
        return this.protocol.concat(URL_UTILS).concat(this.authority).concat(notCompletedUrl);
    }

    private ArrayList<String> getDisallowsFiltered() {
        return this.arrayDisallowFiltered;
    }

    private ArrayList<String> getAllowsFiltered() {
        return this.arrayAllowFiltered;
    }

}
