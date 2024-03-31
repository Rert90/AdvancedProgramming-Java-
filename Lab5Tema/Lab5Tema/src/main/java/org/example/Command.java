package org.example;
import java.io.IOException;

// Interface for commands
public interface Command {
    void execute() throws InvalidCommandException, IOException;
}
