package com.urise.webapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFile {

    public static List<File> getAllFiles(String filePath) {
        Objects.requireNonNull(filePath, "The parameter 'filePath' must not be null.");
        return getAllFiles(new File(filePath));
    }

    public static List<File> getAllFiles(File srcFile) {
        Objects.requireNonNull(srcFile, "The parameter 'srcFile' must not be null.");
        if (!srcFile.exists() || srcFile.isFile() || !srcFile.canRead()) {
            throw new RuntimeException("IO error: " + srcFile.getAbsolutePath());
        }
        return doGetAllFiles(srcFile);
    }

    private static List<File> doGetAllFiles(File srcFile) {
        List<File> fileList = new ArrayList<>();
        doGetAllFilesRecursion(srcFile, fileList);
        return fileList;
    }

    private static void doGetAllFilesRecursion(File srcFile, List<File> fileList) {
        Objects.requireNonNull(srcFile, "The parameter 'srcFile' must not be null.");
        if (!srcFile.exists() || srcFile.isFile() || !srcFile.canRead()) {
            throw new RuntimeException("IO error: " + srcFile.getAbsolutePath());
        }

        File[] files = srcFile.listFiles();

        if (Objects.isNull(files)) {
            return;
        }

        for (File file : files) {
            if (!file.exists()) {
                continue;
            }
            if (file.isFile()) {
                fileList.add(file);
            } else {
                doGetAllFilesRecursion(file, fileList);
            }
        }
    }

    public static void main(String[] args) {
        File srcFile = new File(".//");
        getAllFiles(srcFile).forEach(System.out::println);

        System.out.println("---------------------------------------------------");

        getAllFiles(".//").forEach(System.out::println);
    }
}
