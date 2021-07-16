# CRAWLER - A crawler to vulnerability assessment

This tool was developed for the purpose of a study whose objectives were to crawl and identify one of the most widespread vulnerabilities on the web: the DOM-based XSS. The latter is nothing more than a particular type of XSS vulnerability that occurs entirely in client-side JavaScript and is now increasingly a threat. Although it can be defined as a cross-site scripting attack, it is not possible to resort to traditional methods to defend against this vulnerability, such as server-side contamination detection or web application firewalls, as the vulnerability resides entirely in the client's code and the server is not aware of any attacks. To this end we have implemented and used this tool:

- first we do "crawling": given a seed (eg. http://www.google.com) as input, the crawler returns a set of urls (visited and frontier);
- the "console" implemented in the tool allows you to execute the URLs obtained (visited and frontier) in the browser and, using in parallel a vulnerability analysis tool (eg. Burp Suite), it is possible to identify vulnerabilities (eg. DOM-based XSS or others) in the url obtained during the crawling phase.


## HOW TO USE CRAWLER?

- First download and install Java and MongoDB (https://www.mongodb.com/)
- git clone https://github.com/fradr97/Crawler.git
- cd Crawler
- mvnw compile exec:java -Dexec.mainClass="com.crawler.app.Main"