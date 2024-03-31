package org.example;

import java.io.IOException;

public class ReportCommand implements Command {
    @Override
    public void execute(DocumentRepository repository, String[] args) throws IOException, InvalidCommandException {
        if (args.length != 1) {
            throw new InvalidCommandException("Invalid arguments for report command.");
        }
        repository.generateReport();
    }
}

