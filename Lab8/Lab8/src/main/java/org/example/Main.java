package org.example;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorDAO authorDAO = new AuthorDAO();

        Author author1 = new Author("John Doe", "john@example.com");
        Author author2 = new Author("Jane Smith", "jane@example.com");
        Author author3 = new Author("Alice Johnson", "alice@example.com");

        authorDAO.addAuthor(author1);
        authorDAO.addAuthor(author2);
        authorDAO.addAuthor(author3);
        System.out.println("Authors added successfully!");

        List<Author> allAuthors = authorDAO.getAllAuthors();

        System.out.println("All Authors:");
        for (Author author : allAuthors) {
            System.out.println(author);
        }
    }
}
