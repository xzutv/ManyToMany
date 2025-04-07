package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String nationality;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Book> books;

    public Author() {}

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
        this.books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Nationality: " + nationality;
    }
}
