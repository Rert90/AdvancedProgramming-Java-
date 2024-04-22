package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addAuthor(Author author) {
        try {
            String query = "INSERT INTO Author (name, email) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, author.getName());
            statement.setString(2, author.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            String query = "SELECT * FROM Author";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author();
                author.setAuthorId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("name"));
                author.setEmail(resultSet.getString("email"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

}
