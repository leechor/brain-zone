package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class InOutputStream implements AutoCloseable {
    private static Logger logger = LoggerFactory.getLogger(XmlReader.class);

    public InputStream inputStream;
    public OutputStream outputStream;
    public final String name;

    public InOutputStream(String path) {
        this(null,null, path);
    }


    public InOutputStream(InputStream inputStream, OutputStream outputStream) {
        this(inputStream, outputStream, null);
    }

    public InOutputStream(InputStream inputStream, OutputStream outputStream, String name) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        if(name != null && this.inputStream == null) {
            try {
                this.outputStream = new FileOutputStream(this.name);
            } catch (FileNotFoundException e) {
                logger.error("File outputstream error");
            }
        }
        return this.outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getName() {
        return name;
    }


    void mark() {
        if (this.inputStream.markSupported()) {
            inputStream.mark(Integer.MAX_VALUE);
        } else {
            logger.warn("not support stream mark");
        }
    }

    void reset() {
        try {
            inputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void reopen() {
        if (Strings.isNullOrEmpty(name)) {
            logger.error("文件不存在");
        }

        try {
            this.inputStream = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close() {
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            if (this.outputStream != null) {
                this.outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
