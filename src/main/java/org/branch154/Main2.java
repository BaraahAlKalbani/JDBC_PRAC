package org.branch154;

import java.sql.*;
import java.util.ArrayList;

public class Main2 {
    /**
     * The main method connects to the database, retrieves all student records, and displays them.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:3306/ims";
        final String DB_USER_NAME = System.getenv("DB_USER_NAME");
        final String DB_USER_PASS = System.getenv("DB_USER_PASS");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASS);
            ArrayList<Student> students = getAllStudents(connection);
            displayAllStudents(students);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Retrieves all student records from the database.
     * @param connection The Connection object to use to connect to the database.
     * @return An ArrayList of Student objects representing all student records.
     * @throws SQLException If an error occurs while executing the SQL statement.
     */
    private static ArrayList<Student> getAllStudents(Connection connection) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                Student student = new Student(id, name, email);
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error executing SQL statement: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing statement or result set: " + e.getMessage());
            }
        }

        return students;
    }

    /**
     * Displays all student records in the ArrayList.
     * @param students An ArrayList of Student objects representing all student records.
     */
    private static void displayAllStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + ", NAME: " + student.getName() + ", EMAIL: " + student.getEmail());
        }
    }
}