package com.eny.taskComposite.task.reader;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomTextReader {
    private static final String DEFAULT_PATH = "data/text.txt";
    private static final Logger logger = LogManager.getLogger();

    public String readFileData(String filename) {
        Path path;
        String data;

        if (filename != null && Files.exists(Paths.get(filename))) {
            path = Paths.get(filename);
        } else {
            path = Paths.get(DEFAULT_PATH);
            logger.log(Level.WARN, "No selected file, using default file");
        }

        try {
            data = Files.readString(path);
        } catch (IOException e) {
            logger.log(Level.FATAL, String.format("No default file {%s}", DEFAULT_PATH));
            throw new RuntimeException(String.format("No default file {%s}", DEFAULT_PATH), e);
        }

        return data;
    }
}

