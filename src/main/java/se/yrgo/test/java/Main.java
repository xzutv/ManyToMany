package se.yrgo.test.java;

import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

import jakarta.persistence.*;

public class Main {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Author author1 = new Author("John", "Siberia");
        Author author2 = new Author("Liridona", "Kosovo");
        Author author3 = new Author("Ruby", "Rumania");

        Book book1 = new Book("Karamja Adventures", "1996");
        Book book2 = new Book("Hero of Gilinor", "1923");
        Book book3 = new Book("Theatre of Blood", "2019");
        Book book4 = new Book("Chambers of Xeric", "2007");
        Book book5 = new Book("Tombs of Amascut", "2014");

        Reader r1 = new Reader("Simon", "simon@gmail.com");
        Reader r2 = new Reader("Gunnlis", "Gunnlis@gmail.com");
        Reader r3 = new Reader("James", "James@gmail.com");

        author1.addBook(book1);
        author1.addBook(book2);

        author2.addBook(book3);
        author2.addBook(book4);

        author3.addBook(book5);

        r1.addBook(book1);
        r2.addBook(book2);
        r3.addBook(book3);
        r1.addBook(book4);
        r2.addBook(book5);

        // em.persist(author1);
        // em.persist(author2);
        // em.persist(author3);
        // em.persist(r1);
        // em.persist(r2);
        // em.persist(r3);

        for (Book book : author1.getBooks()) {
            System.out.println(book);
        }

        tx.commit();
        em.close();

    }
}
