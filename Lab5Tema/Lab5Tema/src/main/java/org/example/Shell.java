package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// Shell class for reading commands from the keyboard
public class Shell {
    private final RepositoryManager repositoryManager;
    private final Map<String, Command> commands;

    public Shell(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
        this.commands = new HashMap<>();
        this.commands.put("view", new ViewCommand(""));
        this.commands.put("report", new ReportCommand(repositoryManager));
        this.commands.put("export", new ExportCommand(repositoryManager));
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("Enter command (view, report, export): ");
                String[] input = reader.readLine().trim().split("\\s+", 2);
                if (input.length > 0) {
                    String commandName = input[0];
                    if (commands.containsKey(commandName)) {
                        Command command = commands.get(commandName);
                        if (input.length > 1) {
                            String arguments = input[1];
                            // Set arguments for the command
                            if (command instanceof ViewCommand) {
                                ((ViewCommand) command).setFilePath(arguments);
                            }
                        }
                        command.execute();
                    } else {
                        System.out.println("Invalid command.");
                    }
                }
            }
        } catch (IOException | InvalidCommandException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
