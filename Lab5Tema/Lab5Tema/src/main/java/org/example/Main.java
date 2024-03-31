package org.example;



public class Main {
    public static void main(String[] args) {
        DocumentRepository repository = new DocumentRepository("/Users/olariurobert/IdeaProjects/AdvancedProgramming/AdvancedProgramming-Java-/Lab5/Lab5");
        CommandLineInterface cli = new CommandLineInterface(repository);

        // Example usage of displaying repository content
        repository.displayRepositoryContent();

        // Example usage of executing commands from the CLI
        cli.executeCommand("view 123 document.pdf");
        cli.executeCommand("report");
        cli.executeCommand("export output.json");
    }
}
