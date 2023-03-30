package org.branch166;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        final String DB_URL = "jdbc:mysql://localhost:3306/ims";
        final String DB_USER_NAME = System.getenv("DB_USER_NAME");
        final String DB_USER_PASS = System.getenv("DB_USER_PASS");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASS);
            Statement statement = connection.createStatement();

            statement.execute("select * from student;");

            ResultSet rs = statement.getResultSet();
            //process the result set
            displaySet(rs);

            //Update a Record
            int idToUpdate =3;
            String newName="Lolo";
            statement.executeUpdate("UPDATE student SET name = '"+newName+"' WHERE id = "+idToUpdate+";");
            System.out.println("\nUpdated record with ID: "+idToUpdate+", to Have the NAME: "+newName);
            rs= statement.executeQuery("SELECT * FROM student WHERE id = "+idToUpdate);
            displaySet(rs);

            //Insert new
            String email ="Laith@gmail.com";
            String name="Laith";
            statement.executeUpdate("INSERT INTO student (name, email) VALUES ('"+name+"', '"+email+"');");
            //process the result set
            System.out.println("\nInserted record with name: "+name+", and Have the Email: "+email);
            displayallSet(rs,statement);

            //delete record
            int idToDelete=6;
            statement.executeUpdate("DELETE FROM student WHERE id ="+idToDelete+";");
            //process the result set
            System.out.println("\nDELETED the record with the id: "+idToDelete);
            displayallSet(rs,statement);

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
            }
        }
    }

    /**
     * A helper method that displays the contents of the 'student' table.
     * @param rs the result set to use for displaying the table contents
     * @param statement the statement object used to execute the query
     * @throws SQLException if an error occurs while accessing the database
     */
    public static void displayallSet(ResultSet rs,Statement statement) throws SQLException {
        statement.execute("select * from student;");
        rs = statement.getResultSet();
        while (rs.next())
        {
            int id =rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println("ID: "+id+", NAME: "+name+", EMAIL: "+email);
        }
    }

    /**
     * A helper method that displays the contents of a result set.
     * @param rs the result set to display
     * @throws SQLException if an error occurs while accessing the result set
     */
    public static  void displaySet(ResultSet rs) throws SQLException {
        while (rs.next())
        {
            int id =rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            System.out.println("ID: "+id+", NAME: "+name+", EMAIL: "+email);
        }
    }
}