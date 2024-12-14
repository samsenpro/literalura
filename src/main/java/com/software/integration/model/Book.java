package com.software.integration.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private String languages;

    @Column(name = "download_count")
    @JsonAlias("download_count")
    private Integer downloadCount;

    public Book() {}

    public Book(String title, Author author, String languages, Integer downloadCount) {
        this.title = title;
        this.author = author;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }


    @Override
    public String toString() {
        return "\n" +
                "**********************************Libros**********************************:\n" +
                "Titulo: " + title + "\n" +
                "Autor: " + author.getName() + "\n" +
                "Lenguaje: " + languages + "\n" +
                "Contador de descargas: " + downloadCount + "\n";
    }
}