package org.example;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DocumentRepository {
    private final File masterDirectory;

    public DocumentRepository(String masterDirectoryPath) {
        this.masterDirectory = new File(masterDirectoryPath);
    }

    public void displayRepositoryContent() {
        if (!masterDirectory.exists() || !masterDirectory.isDirectory()) {
            System.out.println("Repository directory doesn't exist or is invalid.");
            return;
        }

        File[] personDirectories = masterDirectory.listFiles(File::isDirectory);
        if (personDirectories != null) {
            for (File personDirectory : personDirectories) {
                System.out.println("Person: " + personDirectory.getName());
                File[] documents = personDirectory.listFiles();
                if (documents != null) {
                    for (File document : documents) {
                        System.out.println("  - " + document.getName());
                    }
                }
            }
        }
    }

    public void viewDocument(String personId, String documentName) throws IOException {
        File documentFile = new File(masterDirectory, personId + File.separator + documentName);
        if (!documentFile.exists()) {
            System.out.println("Document not found.");
            return;
        }

        Desktop.getDesktop().open(documentFile);
    }

    public void generateReport() throws IOException {
        // Implement report generation
        // For simplicity, let's just print a sample report to console
        System.out.println("Generating report...");
        System.out.println("Sample report content");
    }

    public void exportRepository(String outputPath) throws IOException {
        // Implement exporting repository to JSON format
        // For simplicity, let's just print a sample export message
        System.out.println("Exporting repository to JSON format...");
        System.out.println("Sample export content");
    }
}
