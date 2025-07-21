package org.example;

import org.example.entites.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory=new Configuration()
                .configure("hibernate.cfg.xml") // Load the configuration from hibernate.cfg.xml
                .addAnnotatedClass(Student.class) // Register the Student entity class
                .buildSessionFactory(); // Build the session factory
        // use the session fadtory to create a session
        Session session = sessionFactory.getCurrentSession();
        try {

            /// start a transaction
            session.beginTransaction();
            /// create a new student object
            Student student=new Student("Mohamed", "Rashed", "Rashed@emial");
            Student student2=new Student("Noor", "Rashed", "Noor@emial");
            ///save the student object
            session.persist(student);
            session.persist(student2);
            ///commit the transaction
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
}