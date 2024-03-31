package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RepositoryManager {
    private final String masterDirectory;

    public RepositoryManager(String masterDirectory) {
        // Validate masterDirectory
        File directory = new File(masterDirectory);
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid master directory: " + masterDirectory);
        }
        this.masterDirectory = masterDirectory;
    }

    public String getMasterDirectory() {
        return masterDirectory;
    }

    public List<String> getFiles() {
        List<String> fileList = new ArrayList<>();
        File directory = new File(masterDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                // Add only files, excluding directories
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }

        return fileList;
    }

}
