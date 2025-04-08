package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30)
    private String name;

    @Column(length = 40)
    private String email;

    @ManyToMany()
    private List<Book> books;

    public Reader() {};

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Reader [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}
