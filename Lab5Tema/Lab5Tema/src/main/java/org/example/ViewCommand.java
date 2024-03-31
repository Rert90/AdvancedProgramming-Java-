package org.example;
import java.io.IOException;

public class ViewCommand implements Command {
    @Override
    public void execute(DocumentRepository repository, String[] args) throws IOException, InvalidCommandException {
        if (args.length != 3) {
            throw new InvalidCommandException("Invalid arguments for view command.");
        }
        repository.viewDocument(args[1], args[2]);
    }
}
