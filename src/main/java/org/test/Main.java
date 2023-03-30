package org.test;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
       System.out.println(System.getenv("DB_USER_NAME"));
       System.out.println(System.getenv("DB_USER_PASS"));
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ims",
                    System.getenv("DB_USER_NAME"),
                    System.getenv("DB_USER_PASS")
            );
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
            throw new RuntimeException(e);
        }
    }
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