package org.example;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = EntityManagerFactorySingleton.getEntityManagerFactory();
        AuthorRepository authorRepository = new AuthorRepository(entityManagerFactory);

        // Test CRUD operations
        Author author1 = new Author();
        author1.setName("John Doe");
        author1.setEmail("john@example.com");
        authorRepository.createAuthor(author1);

        Author author2 = new Author();
        author2.setName("Jane Smith");
        author2.setEmail("jane@example.com");
        authorRepository.createAuthor(author2);

        Author foundAuthor = authorRepository.findById(1);
        System.out.println("Found author: " + foundAuthor);

        List<Author> authorsWithNameJane = authorRepository.findByName("Jane");
        System.out.println("Authors with name containing 'Jane': " + authorsWithNameJane);

        // Don't forget to close EntityManagerFactory when the application shuts down
        EntityManagerFactorySingleton.closeEntityManagerFactory();
    }
}
