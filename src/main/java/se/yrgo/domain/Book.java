package se.yrgo.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30)
    private String title;

    @Column(length = 10)
    private String publicationYear;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers;

    public Book() {}

    public Book(String title, String publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.readers = new ArrayList<Reader>();
    }

    public void addReader(Reader reader) {
        this.readers.add(reader);
    }

    public List<Reader> getReaders() {
        return readers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
