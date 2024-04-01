package org.example;



public class Main {
    public static void main(String[] args) {

        RepositoryManager repositoryManager = new RepositoryManager("/Users/olariurobert/IdeaProjects/AdvancedProgramming/AdvancedProgramming-Java-/");
        Shell shell = new Shell(repositoryManager);
        shell.run();
    }
}

