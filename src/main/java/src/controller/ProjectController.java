package src.controller;

import src.model.Project;
import src.model.Task;
import src.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects (name, " +
                "description, " +
                "createdAt, " +
                "updatedAt) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Error at creating project. ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE projects SET " +
                "name = ?," +
                "description = ?," +
                "createdAt = ?," +
                "updatedAt = ?" +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));

            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Error at updating project. ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
}
