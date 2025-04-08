package se.yrgo.test.java;

import se.yrgo.domain.Author;
import se.yrgo.domain.Book;
import se.yrgo.domain.Reader;

import java.util.*;

import jakarta.persistence.*;

public class Main {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {

        // Uppgift 1

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Author author1 = new Author("John", "Siberia");
        Author author2 = new Author("Liridona", "Kosovo");
        Author author3 = new Author("Ruby", "Rumania");
        Author author4 = new Author("Kamel", "Russia");

        Book book1 = new Book("Karamja Adventures", "Adventure", "1996");
        Book book2 = new Book("Hero of Gilinor", "Action", "1923");
        Book book3 = new Book("Theatre of Blood", "Horror", "2019");
        Book book4 = new Book("Chambers of Xeric", "Action", "2007");
        Book book5 = new Book("Tombs of Amascut", "Adventure", "2014");
        Book book6 = new Book("Lallare", "Comedy", "2003");

        Reader r1 = new Reader("Simon", "simon@gmail.com");
        Reader r2 = new Reader("Gunnlis", "Gunnlis@gmail.com");
        Reader r3 = new Reader("James", "James@gmail.com");

        author1.addBook(book1);
        author1.addBook(book2);

        author2.addBook(book3);
        author2.addBook(book4);

        author3.addBook(book5);

        author4.addBook(book6);

        r1.addBook(book1);
        r2.addBook(book2);
        r3.addBook(book3);

        r1.addBook(book4);
        r2.addBook(book5);

        em.persist(author1);
        em.persist(author2);
        em.persist(author3);
        em.persist(author4);
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);

        // Author author = em.find(Author.class, 1);
        // Book book = em.find(Book.class, 2);
        // Reader reader = em.find(Reader.class, 11);

        // System.out.println(author);
        // System.out.println(book);
        // System.out.println(reader);

        // Uppgift 2:

        String nameRequest = "john";
        List<Book> result = em
                .createQuery("select b from Author as a join a.books as b where lower(a.name) =:name", Book.class)
                .setParameter("name", nameRequest).getResultList();

        for (Book book : result) {
            System.out.println(nameRequest + " has written " + book);
        }

        // Uppgift 3

        Book findBook = em.find(Book.class, 8);
        List<Reader> readers = em.createQuery("from Reader as r where :title member of r.books", Reader.class)
                .setParameter("title", findBook).getResultList();

        for (Reader reader : readers) {
            System.out.println("Reader: " + reader + " reads " + findBook);
        }

        // Uppgift 4

        List<Author> results = em
                .createQuery("select distinct a from Author as a join a.books as b join b.readers as r", Author.class)
                .getResultList();

        for (Author obj : results) {
            System.out.println("Authors having readers: " + obj);
        }

        // Uppgift 5

        List<Object[]> countBookResult = em
                .createQuery("select a.name, count(b) from Author as a join a.books as b group by a.name",
                        Object[].class)
                .getResultList();

        for (Object[] a : countBookResult) {
            System.out.println(a[0] + " has " + a[1] + " books");
        }

        // Övning 6
        String genre = "Adventure";

        List<Book> searchGenreResult = em.createNamedQuery("searchByGenre", Book.class).setParameter("genre", genre)
                .getResultList();

        for (Book b : searchGenreResult) {
            System.out.println("Books with " + genre + " as genre: " + b);
        }

        tx.commit();
        em.close();

    }
}
