package org.example;
import java.io.IOException;
import java.util.*;

public class CommandLineInterface {
    private final DocumentRepository repository;
    private final Map<String, Command> commands;

    public CommandLineInterface(DocumentRepository repository) {
        this.repository = repository;
        this.commands = new HashMap<>();
        // Initialize commands
        commands.put("view", new ViewCommand());
        commands.put("report", new ReportCommand());
        commands.put("export", new ExportCommand());
    }

    public void executeCommand(String commandLine) {
        String[] tokens = commandLine.split("\\s+");
        if (tokens.length == 0) {
            System.out.println("Invalid command.");
            return;
        }

        String commandName = tokens[0];
        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command: " + commandName);
            return;
        }

        try {
            command.execute(repository, tokens);
        } catch (IOException | InvalidCommandException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
