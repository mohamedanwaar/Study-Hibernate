package org.example;

import org.example.entites.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteStudentDemo {
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
            Student studentToDelete = session.find(Student.class, studentId); // Find the student with id 1
            System.out.println("Deleting student: " + studentToDelete);
            session.remove(studentToDelete); // Delete the student object
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