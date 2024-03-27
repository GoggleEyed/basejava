package com.goggleeyed.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    private static int depth = 0;

    private static boolean lastFile = false;

    private static void listFiles(File dir) {
        String string = (depth == 0 ? "" : new String(new char[depth - 1]).replace("\0", "│   ")
                + (lastFile ? "└── " : "├── ")) + dir.getName();
        System.out.println(string);

        if (dir.isDirectory()) {
            depth++;
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length - 1; i++) {
                listFiles(files[i]);
            }
            lastFile = true;
            listFiles(files[files.length - 1]);
            lastFile = false;
            depth--;
        }
    }

    public static void main(String[] args) {
        File filePath = new File("./.gitignore");
        try {
            System.out.println(filePath.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("./src/com/goggleeyed/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        listFiles(dir);
    }
}
