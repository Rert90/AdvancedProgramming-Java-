package org.example;
import java.io.IOException;

public class ExportCommand implements Command {
    @Override
    public void execute(DocumentRepository repository, String[] args) throws IOException, InvalidCommandException {
        if (args.length != 2) {
            throw new InvalidCommandException("Invalid arguments for export command.");
        }
        repository.exportRepository(args[1]);
    }
}
