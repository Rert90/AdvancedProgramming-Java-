package org.example;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

// Command for creating and opening an HTML report
public class ReportCommand implements Command {
    private final RepositoryManager repositoryManager;

    public ReportCommand(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @Override
    public void execute() throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\rober\\IdeaProjects\\AdvancedProgramming-Java-\\Lab5Tema\\templates"));
        configuration.setDefaultEncoding("UTF-8");


        // Create data model
        Map<String, Object> data = new HashMap<>();
        data.put("files", repositoryManager.getFiles());

        // Get template
        Template template = configuration.getTemplate("report_template.ftl");

        // Process template
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(data, stringWriter);
        } catch (TemplateException e) {
            throw new IOException("Error processing FreeMarker template", e);
        }

        try (FileWriter writer = new FileWriter("repository_report.html")) {
            writer.write(stringWriter.toString());
        }

        Desktop.getDesktop().open(new File("repository_report.html"));
    }

}
