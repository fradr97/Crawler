package com.crawler.app.Config;

public final class Strings {

    private Strings() { }

    public static final String APPLICATION_TITLE = "Crawler";

    public static final String LABEL_OUR_NAME_STRING = "<html><p>&nbsp;&nbsp;&nbsp;Francesco di Rito<br/>" +
            "&nbsp;&nbsp;&nbsp;Emanuela Guglielmi<br/><br/></p></html>";

    public static final String TITLE_WELCOME_STRING = "Crawling and Vulnerability Assessment";
    public static final String START_STRING = "Start";
    public static final String EXIT_STRING = "Exit";

    /** DATABASE CONFIGURATION & STRUCTURE */
    public static final String DRIVER_MONGO = "org.mongodb.driver";
    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String DATABASE_NAME = "crawlerDB";

    public static final String CRAWLING_COLLECTION = "crawling";

    public static final String ID_SEED = "idSeed";
    public static final String SEED = "seed";
    public static final String CRAWL_FRONTIER = "crawlFrontier";

    public static final String SET = "$set";

    /** TEST INTERNET CONNECTION */
    public static final String GOOGLE = "google.com";
    public static final String FACEBOOK = "facebook.com";

    /** SUBMENU BAR */
    public static final String FILE = "File";
    public static final String EDIT = "Edit";
    public static final String VIEW = "View";
    public static final String NEW = "   New   ";
    public static final String EXIT = "   Exit   ";
    public static final String DELETE_ONE = "   Delete one   ";
    public static final String DELETE_ALL = "   Delete all   ";
    public static final String HISTORY = "   History   ";

    /** BUTTONS and MESSAGES */
    public static final String INTERNET_NOT_CONNECTED_STRING = "Internet not connected!";
    public static final String MONGO_NOT_FOUND_STRING = "MongoDB not found!";
    public static final String IN_PROGRESS_STRING = "In progress ...";
    public static final String INSERT_SEED_URL_STRING = "Enter the seed url: ";
    public static final String INSERT_WEIGHT_STRING = "Enter the number of urls to 'visit': ";
    public static final String INVALID_URL_WEIGHT_STRING = "Invalid url or weight!";
    public static final String INVALID_URL_STRING = "Invalid url!";
    public static final String INVALID_URL_ROBOTS_STRING = "Url blocked by robots.txt!";
    public static final String DELETED_SUCCESSFULLY_STRING = "Url successfully deleted";
    public static final String PLEASE_SELECT_SEED_STRING = "Please, select a seed!";
    public static final String FILE_DONE_STRING = "Operation done";
    public static final String PROBLEM_URL_STRING = "There was a problem with a url";
    public static final String ERROR_CONSOLE_STRING = "There is an error with your command!";
    public static final String INSERT_BROWSER_STRING = "Enter the browser path manually";
    public static final String DELETE_EVERYTHING_STRING = "Are you sure you want to delete everything?";
    public static final String DELETE_ONE_STRING = "Are you sure you want to delete selected seed?";
    public static final String EMPTY_PATH_FILE_STRING = " Empty path";
    public static final String ERROR_FILE_STRING = "Change the path or file name.";
    public static final String EXISTS_FILE_STRING = "File already exists in this path!";
    public static final String INSERT_PATH_STRING = "Insert path to save file (e.g. ";
    public static final String ENTER_COMMAND_EXEC_STRING = "Select a browser or enter a generic command:";
    public static final String ENTER_RANGE_ID_STRING = "Enter the id range (from, to) and the set of URLs:";
    public static final String DELETED_STRING = " Deleted";
    public static final String MESSAGE_STRING = " Message";
    public static final String SAVE_STRING = " Save Frontier";
    public static final String ERROR_STRING = " Error!";
    public static final String DELETE_STRING = " Delete";
    public static final String CONSOLE_STRING = " Console";
    public static final String NO_SEEDS_STRING = "NO SEEDS";
    public static final String YES_STRING = "YES";
    public static final String NO_STRING = "NO";
    public static final String CANCEL_STRING = "CANCEL";
    public static final String CLOSE_STRING = "CLOSE";
    public static final String RUN_INDIVIDUAL_LINK_STRING = "RUN INDIVIDUAL LINK";
    public static final String RUN_ALL_LINKS_STRING = "RUN ALL LINKS";
    public static final String CONFIRM_STRING = "CONFIRM";
    public static final String NEXT_URL_STRING = "NEXT URL";

    /** SPACES */
    public static final String EMPTY_STRING = "";
    public static final String ONE_SPACE = " ";
    public static final String FOUR_SPACES = "    ";

    public static final String NEXT_LINE_STRING = "\n";
    public static final String NEXT_LINE_STRING2 = "\n\n";

    /** CRAWLING */
    public static final String START_CRAWLING_STRING = "\n    Start Crawling! ";

    public static final String CRAWLING_ATTRIBUTES = "\n    Seed:\n    Extracted urls:\n    Visited:\n    " +
            "In Frontier:\n\n\n    Seed:\n    Extracted urls:\n    Visited:\n    In Frontier:\n\n\n    " +
            "Seed:\n    Extracted urls:\n    Visited:\n    In Frontier:";

    public static final String A_HREF = "a[href]";
    public static final String ABS_HREF = "abs:href";

    /** ROBOTS.TXT */
    public static final String DISALLOW = "Disallow";
    public static final String ALLOW = "Allow";
    public static final String USER_AGENT_STAR = "User-agent: *";
    public static final String URL_UTILS = "://";
    public static final String ROBOTS = "/robots.txt";

    /** FILE */
    public static final String SLASH = "/";
    public static final String BACK_SLASH = "\\";

    public static final String HOME_DIRECTORY = "user.home";
    public static final String TXT_EXTENSION = ".txt";
    public static final String NAME_FILE = "fileName.txt";
    public static final String RIGHT_BRACKET = ")";

    /** TABS & TABLE*/
    public static final String COLUMN_ID = "Id";
    public static final String SEED_TAB = "Seed";
    public static final String VISITED = "Visited";
    public static final String FRONTIER = "Frontier";

    /** CONSOLE */
    public static final String FRAME_CONSOLE_PATH = "Crawling.GUI.Panels.Console.ConsoleFrame";

    public static final String WHICH_LINUX = "which";

    public static final String EDGE = "edge";
    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    public static final String PROGRAM_FILES_STRING = "ProgramFiles";
    public static final String EDGE_PATH_WINDOWS = "\\Microsoft\\Edge\\Application\\msedge.exe";
    public static final String FIREFOX_PATH_WINDOWS = "\\Mozilla Firefox\\firefox.exe";
    public static final String CHROME_PATH_WINDOWS = "\\Google\\Chrome\\Application\\chrome.exe";

    /** OS */
    public static final String OS_NAME = "os.name";

    public static final String WINDOWS = "WINDOWS";
    public static final String LINUX = "LINUX";
    public static final String OTHER_OS = "OTHER";

    /** FONTS */
    public static final String ARIAL = "Arial";
    public static final String ALGERIAN = "Algerian";

}
