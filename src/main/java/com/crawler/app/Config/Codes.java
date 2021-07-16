package com.crawler.app.Config;

public final class Codes {

    private Codes() { }

    /** MONGO STATUS */
    public enum MONGO_STATUS {
            ON, OFF
    }

    /** FILE */
    public static final int OK = 1;
    public static final int EMPTY = -1;
    public static final int NONE = -2;
    public static final int ERROR = -3;
    public static final int EXISTS = -4;

    /** CONSOLE */
    public static final int EXEC_ONLY_COMMAND = 1;
    public static final int EXEC_COMMAND_AND_RANGE_SINGLE = 2;
    public static final int EXEC_COMMAND_AND_RANGE_ALL = 3;
    public static final int ERROR_CONSOLE = -1;
}
