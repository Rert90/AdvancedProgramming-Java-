package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDAO {
    private Connection connection;

    public PublisherDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void addPublisher(Publisher publisher) {
        // Add a new publisher to the database
        try {
            String query = "INSERT INTO Publisher (name, location) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getLocation());
            statement.executeUpdate();

            // Retrieve the auto-generated keys (if any)
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                publisher.setPublisherId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implement the getAllPublishers method to retrieve all publishers from the database
    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        try {
            String query = "SELECT * FROM Publisher";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherId(resultSet.getInt("publisher_id"));
                publisher.setName(resultSet.getString("name"));
                publisher.setLocation(resultSet.getString("location"));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    // Implement other CRUD methods as needed
}
