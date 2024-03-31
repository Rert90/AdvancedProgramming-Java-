package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// Command for exporting the repository to JSON using Jackson
public class ExportCommand implements Command {
    private final RepositoryManager repositoryManager;

    public ExportCommand(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File directory = new File(repositoryManager.getMasterDirectory());
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (!file.getName().equals("report_template.ftl")) { // Exclude template file from export
                    String jsonFileName = file.getName().replace(".", "_") + ".json";
                    File jsonFile = new File(jsonFileName);
                    mapper.writeValue(jsonFile, file);
                }
            }
        }
    }
}
