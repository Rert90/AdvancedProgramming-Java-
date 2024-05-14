package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorDAO authorDAO = new AuthorDAO();
        PublisherDAO publisherDAO = new PublisherDAO();
        BookDAO bookDAO = new BookDAO();


        Author author1 = new Author();
        author1.setName("John Doe");
        author1.setEmail("john@example.com");
        authorDAO.addAuthor(author1);

        Author author2 = new Author();
        author2.setName("Jane Smith");
        author2.setEmail("jane@example.com");
        authorDAO.addAuthor(author2);

        Publisher publisher1 = new Publisher();
        publisher1.setName("Publisher A");
        publisher1.setLocation("Location A");
        publisherDAO.addPublisher(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Publisher B");
        publisher2.setLocation("Location B");
        publisherDAO.addPublisher(publisher2);

        System.out.println("Authors:");
        List<Author> authors = authorDAO.getAllAuthors();
        for (Author author : authors) {
            System.out.println(author);
        }

        System.out.println("\nPublishers:");
        List<Publisher> publishers = publisherDAO.getAllPublishers();
        for (Publisher publisher : publishers) {
            System.out.println(publisher);
        }
    }
}
