package org.example;



public class Main {
    public static void main(String[] args) {

        RepositoryManager repositoryManager = new RepositoryManager("C:\\Users\\rober\\IdeaProjects\\AdvancedProgramming-Java-\\Lab5Tema\\templates");
        Shell shell = new Shell(repositoryManager);
        shell.run();
    }
}

