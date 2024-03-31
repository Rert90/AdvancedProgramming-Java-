package org.example;

import java.io.IOException;

public interface Command {
    void execute(DocumentRepository repository, String[] args) throws IOException, InvalidCommandException;
}
