package com.crawler.app.Utils.Components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.crawler.app.Config.Codes.*;
import static com.crawler.app.Config.Strings.*;

public class MyFile {
    private String path;

    public int createFile(String path) {
        this.path = path;

        try {
            if (path.equals(EMPTY_STRING)) {
                return EMPTY;
            }
            if (path.contains(System.getProperty(HOME_DIRECTORY)) && path.endsWith(TXT_EXTENSION)) {
                File myFile = new File(path);
                if (myFile.createNewFile())
                    return OK;
                else
                    return EXISTS;
            }
        } catch (IOException e) {
            return ERROR;
        }
        return NONE;
    }

    public void writeUrlFile(List<String> array) throws IOException {
        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(this.path), StandardCharsets.UTF_8))) {
            for (String a : array) {
                out.write(a.concat(NEXT_LINE_STRING));
            }
        }
    }
}