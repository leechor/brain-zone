package org.licho.brain.concrete;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * file stream operator
 *
 */
@Slf4j
public class FileStreamOperator {

    public static InputStream CreateReadStream(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("File not found: " + fileName);
            return null;
        }

        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
