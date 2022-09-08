package src.controller;

import src.model.Task;
import src.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public void save(Task task) throws RuntimeException {

        String sql = "INSERT INTO tasks (projectId," +
                " name," +
                " description," +
                " completed," +
                " notes," +
                " deadLine," +
                " createdAt," +
                " updatedAt) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getProjectid());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa " +  e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        // Preparing query
        String sql = "UPDATE tasks SET " +
                "projectId = ?," +
                "name = ?," +
                "description = ?," +
                "notes = ?," +
                "completed = ?," +
                "deadLine = ?," +
                "createdAt = ?," +
                "updatedAt = ? " +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            // Creating connection with DB
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getProjectid());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadLine().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar task ", ex);
        } finally {
            // Close DB connection
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(Integer taskId) throws RuntimeException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);

            statement.execute();

        } catch (Exception ex) {
             throw new RuntimeException("Erro ao deletar a tarefa ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(Integer projectId) {

        String sql = "SELECT * FROM tasks WHERE projectId = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);

            // Returned data from DB
            resultSet = statement.executeQuery();

            // Execute while there is tasks to get
            while (resultSet.next()) {
                Task task = new Task();

                task.setId(resultSet.getInt("id"));
                task.setProjectid(resultSet.getInt("projectId"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setCompleted(resultSet.getBoolean("completed"));
                task.setDeadLine(resultSet.getDate("deadLine"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                tasks.add(task);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar as tasks ", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return tasks;
    }
}
