package org.licho.brain.example.war;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class ConvertEnums {

    public static void main(String[] args) throws FileNotFoundException {
        var path = "D:\\project\\simio_project\\SimioEnums";
        File file = new File(path);
        for (var f : file.listFiles()) {
            if (f.isFile() && f.getName().endsWith("cs")) {
                StringBuilder sb = new StringBuilder();
                sb.append("package com.zdpx.cctpp.simioEnums;");

                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    boolean skip = false;
                    for (String line; (line = br.readLine()) != null; ) {
                        if (skip) {
                            skip = false;
                            continue;
                        } else if (line.contains("using System;")) {
                            continue;
                        } else if (line.contains("namespace")) {
                            skip = true;
                            continue;
                        } else if (line.trim().startsWith("//")) {
                            continue;
                        } else if (line.trim().endsWith("}")) {
                            sb.append(line);
                            skip = true;
                            continue;
                        } else {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                        }
                    }

                    var ff = new File("D:\\project\\cctppserver\\simulation\\src\\main\\java\\com\\zdpx\\cctpp" +
                            "\\simioEnums\\" + f.getName().replace(".cs", ".java"));
                    if (!ff.exists()) {
                        ff.createNewFile();
                    }
                    try (FileWriter fw = new FileWriter(ff)) {
                        fw.write(sb.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
