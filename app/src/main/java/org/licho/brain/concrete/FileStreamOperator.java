package org.licho.brain.concrete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 */
public class FileStreamOperator {
    public static InputStream CreateReadStream(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try {
            FileInputStream stream = new FileInputStream(file);
            return stream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
