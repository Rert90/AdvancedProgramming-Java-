package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class RepositoryException extends Exception {
    public RepositoryException(String message) {
        super(message);
    }
}

public class Repository {
    private String masterDirectory;

    public Repository(String masterDirectory) {
        this.masterDirectory = masterDirectory;
    }

    public void displayRepositoryContent() throws RepositoryException {
        File directory = new File(masterDirectory);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new RepositoryException("Master directory does not exist or is not a directory.");
        }

        // List all person directories
        File[] personDirectories = directory.listFiles();
        if (personDirectories != null) {
            for (File personDirectory : personDirectories) {
                if (personDirectory.isDirectory()) {
                    System.out.println("Person Directory: " + personDirectory.getName());

                    // List all files in the person directory
                    File[] files = personDirectory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            System.out.println(" - " + file.getName());
                        }
                    }
                }
            }
        } else {
            throw new RepositoryException("Error accessing person directories.");
        }
    }
}
