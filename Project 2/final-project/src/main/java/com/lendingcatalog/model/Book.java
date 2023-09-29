package com.lendingcatalog.model;

import java.time.LocalDate;
import java.util.UUID;


public class Book implements CatalogItem {
    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;

    public Book(String title, String author, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getCreator() {
        return null;
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public boolean matchesName(String searchStr) {
        return title.toLowerCase().contains(searchStr.toLowerCase());
    }

    public Book(String id, String title, String author, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return publishDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();

    }

    @Override
    public String toString() {
        return "* " + title + System.lineSeparator()
                + " - Written by: " + author + System.lineSeparator()
                + " - Published: " + publishDate + System.lineSeparator()
                + " - Id: " + id;
    }
}
