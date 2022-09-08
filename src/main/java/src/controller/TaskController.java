package src.controller;

import src.model.Task;
import src.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TaskController {



    public void save(Task task) {

    }

    public void update(Task task) {

    }

    public void removeById(Integer taskId) throws SQLException {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);

            statement.execute();

        } catch (SQLException ex) {
             throw new SQLException("Erro ao deletar a tarefa", ex);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }

    public List<Task> getAll(Integer projectId) {
        return null;
    }
}
