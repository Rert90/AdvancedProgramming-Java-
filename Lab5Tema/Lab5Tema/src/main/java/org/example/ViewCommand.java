package org.example;
import java.awt.Desktop;
import java.io.IOException;
import java.io.File;

// Command for viewing a document
public class ViewCommand implements Command {
    private String filePath;

    public ViewCommand(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Error: File path is missing.");
            return;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        Desktop.getDesktop().open(file);
    }
}
