package com.zdpx.cctpp.concrete;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 */
public class TempFileOperator {
    private static String createTempPath(String relatePath) {
        String simioTmpPath = System.getProperty("java.io.tmpdir") +"Simio";
        try {
            var p = Path.of(simioTmpPath);
            if (!p.toFile().exists()) {
                Files.createDirectory(p);
            }
            var fullPath =simioTmpPath + relatePath;
            var fp = Path.of(simioTmpPath + relatePath);;
            if (!fp.toFile().exists()) {
                Files.createDirectory(fp);
            }
            return fullPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createTempDirectoryGuid() {
        return TempFileOperator.createTempPath(Guid.NewGuid().toString());
    }

    public static String createTempFile() {
        return TempFileOperator.createTempFile(".tmp");
    }

    public static String createTempFile(String ext) {
        String path = TempFileOperator.createTempPath("Temp");
        return path + File.separator + Guid.NewGuid().toString() + ext;
    }
}
