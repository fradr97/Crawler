package com.crawler.app.Utils;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import static com.crawler.app.Config.Strings.*;

public final class GenericsMethods {

    private GenericsMethods() {
    }

    /**
     * @return Sistema operativo in uso
     */
    public static String getOS() {
        if (System.getProperty(OS_NAME).toUpperCase().contains(WINDOWS)) {
            return WINDOWS;
        } else if (System.getProperty(OS_NAME).toUpperCase().contains(LINUX)) {
            return LINUX;
        }
        /*else if (System.getProperty(OS_NAME).toUpperCase().contains(MAC)) {
            return MAC;
        }*/
        return OTHER_OS;
    }

    /**
     * @return se la connessione Internet è presente o meno
     */
    public static boolean checkInternetConnection() {
        try {
            return isHostAvailable(GOOGLE) || isHostAvailable(FACEBOOK);
        } catch (IOException ignored) {
            return false;
        }
    }

    private static boolean isHostAvailable(String hostName) throws IOException {
        try (Socket socket = new Socket()) {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);

            return true;
        } catch (UnknownHostException unknownHost) {
            return false;
        }
    }

    /**
     * @param browser da cui ottenere il path
     * @return il path al browser specificato
     * Si applica solo ai sistemi Linux
     */
    public static String getBrowserPath(String browser) {
        try {
            Scanner s = new Scanner(Runtime.getRuntime()
                    .exec(WHICH_LINUX.concat(ONE_SPACE).concat(browser))
                    .getInputStream());

            return s.hasNext() ? s.next() : EMPTY_STRING;
        } catch (Exception ex) {
            return EMPTY_STRING;
        }
    }
}
