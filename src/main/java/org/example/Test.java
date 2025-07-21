package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
    public static void main(String[] args) {
        String jdbcurl = "jdbc:mysql://localhost:3306/student_tracker";
        String user = "Rashed";
        String password = "Rashed1234";
        try {
            Connection connection = DriverManager.getConnection(jdbcurl, user, password);
            System.out.println("Connection successful!");
        } catch (Exception e) {

        }
    }

}
