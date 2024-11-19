package com.simeon.lab3.dbcommunication;

import com.simeon.lab3.qualifiers.HistoryBean;
import com.simeon.lab3.qualifiers.HistoryType;
import com.simeon.lab3.services.History;
import com.simeon.lab3.dto.CheckResult;
import com.simeon.lab3.exceptions.DBConnectException;
import com.simeon.lab3.exceptions.DBWritingException;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@HistoryBean(HistoryType.DATABASE)
public class DatabaseHistory implements History {
    private final Connection connection;
    private final List<CheckResult> history = new LinkedList<>();

    public DatabaseHistory() {
        String url = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        connection = connectToDatabase(url, dbUser, dbPassword);

        createTable();
        loadHistory();
    }

    private Connection connectToDatabase(String url, String dbUser, String dbPassword) {
        try {
            return DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new DBConnectException("Failed to connect to database %s".formatted(url));
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS history (" +
                            "id SERIAL PRIMARY KEY, " +
                            "x DOUBLE PRECISION NOT NULL , " +
                            "y DOUBLE PRECISION NOT NULL , " +
                            "r DOUBLE PRECISION NOT NULL ," +
                            "createdAt DATE NOT NULL ," +
                            "workingTime INTEGER NOT NULL ," +
                            "result BOOLEAN NOT NULL )");
        } catch (SQLException e) {
            throw new DBConnectException("Failed to create table");
        }
    }
    @PreDestroy
    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DBConnectException("Failed to close connection");
        }
    }

    private void loadHistory() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT (x, y, r, result, workingtime, createdAt) FROM history");
            while (resultSet.next()) {
                history.add(new CheckResult(
                        resultSet.getBigDecimal("x"),
                        resultSet.getBigDecimal("y"),
                        resultSet.getBigDecimal("r"),
                        resultSet.getBoolean("result"),
                        resultSet.getInt("workingTime"),
                        resultSet.getDate("createdAt").toLocalDate().atStartOfDay()
                ));
            }
        } catch (SQLException e) {
            throw new DBWritingException();
        }
    }

    @Override
    public List<CheckResult> getResultList() {
        return history;
    }

    @Override
    public void addResult(CheckResult result) {
        history.add(result);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO history (x, y, r, createdAt, workingTime, result) " +
                        "VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setBigDecimal(1, result.getX());
            preparedStatement.setBigDecimal(2, result.getY());
            preparedStatement.setBigDecimal(3, result.getR());
            preparedStatement.setDate(4, Date.valueOf(result.getCreatedAt().toLocalDate()));
            preparedStatement.setLong(5, result.getWorkingTime());
            preparedStatement.setBoolean(6, result.isHit());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DBWritingException();
        }
    }
}
