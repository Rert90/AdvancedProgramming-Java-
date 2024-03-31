package org.example;

// Exception for invalid commands
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
