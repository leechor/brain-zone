package org.licho.brain.concrete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class FileInOutputStream extends InOutputStream {


    public FileInOutputStream(String path) {
        super(path);

        InputStream i = null;
        try {
            var f = new File(path);
            if (!f.exists()) {
                f.createNewFile();
            }
            i = new FileInputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.inputStream = i;
    }

    @Override
    public OutputStream getOutputStream() {
        if (name != null && outputStream == null) {
            try {
                this.outputStream = new FileOutputStream(this.name);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return this.outputStream;
    }


}
