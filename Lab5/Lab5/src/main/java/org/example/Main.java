package org.example;

public class Main {
    public static void main(String[] args) throws RepositoryException {
        Repository repository = new Repository("/Users/olariurobert/IdeaProjects/AdvancedProgramming/AdvancedProgramming-Java-/Lab5/Lab5");
        repository.displayRepositoryContent();
    }
}