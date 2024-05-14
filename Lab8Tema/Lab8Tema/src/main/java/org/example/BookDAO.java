package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addBook(Book book) {
        try {
            String query = "INSERT INTO Book (title, isbn, publication_year, author_id, publisher_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getIsbn());
            statement.setInt(3, book.getPublicationYear());
            statement.setInt(4, book.getAuthorId());
            statement.setInt(5, book.getPublisherId());  // Make sure you are setting the publisher ID
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setBookId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateBook(Book updatedBook) {
        try {
            String query = "UPDATE Book SET title = ?, isbn = ?, publication_year = ?, author_id = ? WHERE book_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, updatedBook.getTitle());
            statement.setString(2, updatedBook.getIsbn());
            statement.setInt(3, updatedBook.getPublicationYear());
            statement.setInt(4, updatedBook.getAuthorId());
            statement.setInt(5, updatedBook.getBookId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            String query = "SELECT * FROM Book";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setAuthorId(resultSet.getInt("author_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Book getBookById(int bookId) {
        Book book = null;
        try {
            String query = "SELECT * FROM Book WHERE book_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setAuthorId(resultSet.getInt("author_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


}
