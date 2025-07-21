package org.example;

import org.example.entites.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdataStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory=new Configuration()
                .configure("hibernate.cfg.xml") // Load the configuration from hibernate.cfg.xml
                .addAnnotatedClass(Student.class) // Register the Student entity class
                .buildSessionFactory(); // Build the session factory
        // use the session fadtory to create a session
        Session session = sessionFactory.getCurrentSession();
        try {

            int studentId = 1; // The ID of the student to update
            /// start a transaction
            session.beginTransaction();
            System.out.println( "Updating student with ID: " + studentId);
            /// retrieve the student object based on the id
            Student studentToUpdate = session.find(Student.class, studentId); // Find the student with id 1
            studentToUpdate.setEmail("mmohamed70@gmail.com"); // Update the email of the student
            System.out.println("--------------------------------------");
            System.out.println("set the last name for all student to Rashed ");

            /// Update the email of all students with last name "Rashed"
            session.createQuery(" update Student  set lastName='Rashed' WHERE id>0 ",Student.class) // Create a query to update the last name of students
                    .executeUpdate(); // Execute the update query to change the last name of students with email starting with "Rashed"
            session.getTransaction().commit();

        }
        catch (Exception e) {
            session.getTransaction().rollback();/// this if an error occurs, rollback the transaction (means undo all the changes made in the transaction)

        }
        finally {
            /// close the session factory
            sessionFactory.close();
        }

    }

    private static void displayStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}