package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private Connection connection;

    public GenreDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addGenre(Genre genre) {
        // Add a new genre to the database
        try {
            String query = "INSERT INTO Genre (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, genre.getName());
            statement.executeUpdate();

            // Retrieve the auto-generated keys (if any)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                genre.setGenreId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement the getAllGenres method to retrieve all genres from the database
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        try {
            String query = "SELECT * FROM Genre";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setGenreId(resultSet.getInt("genre_id"));
                genre.setName(resultSet.getString("name"));
                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    // Implement other CRUD methods as needed
}
