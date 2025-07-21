package org.example;

import org.example.entites.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ReadStudentDemo {
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
            /// reteive a student object based on the id
             Student student=session.find(Student.class, 1); // Find the student with id 1
            /// print the student object
            System.out.println("Retrieved Student: " + student);
            //------------------------------------------------------------------------------------------

            List<Student > students =session.createQuery("from Student", Student.class) // Create a query to retrieve all students
                    .getResultList(); // Execute the query and get the result list
            /// print all students
            System.out.println("------------------------------------------------------");
            System.out.println("All Students: ");
            displayStudents(students);
            //------------------------------------------------------------------------------------------
            /// Query to find students with last name "Rashed"
            List<Student> rashedStudents=session
                    .createQuery("from Student s where lastName='Rashed'", Student.class) // Create a query to find students with last name "Rashed"
                    .getResultList(); // Execute the query and get the result list
            /// print students with last name "Rashed"
            System.out.println("------------------------------------------------------");
            System.out.println("Students with last name 'Rashed': ");
            displayStudents(rashedStudents);
            //-------------------------------------------------------------------------------------------
            /// Query to find students with Email start with "Rashed"
            List<Student> emialsStudents=session
                    .createQuery("from Student s where email like 'Rashed%'",Student.class)
                            .getResultList(); // Create a query to find students with email starting with "Rashed"
            /// print students with email starting with "Rashed"
            System.out.println("------------------------------------------------------");
            System.out.println("Students with email starting with 'Rashed': ");
            displayStudents(emialsStudents);
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

    private static void displayStudents(List<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}