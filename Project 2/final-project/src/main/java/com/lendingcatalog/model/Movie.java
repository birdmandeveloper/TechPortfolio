package com.lendingcatalog.model;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Movie implements CatalogItem {
    private String id;
    private String title;
    private String director;
    private LocalDate releaseDate;

    public Movie(String title, String director, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.id = UUID.randomUUID().toString();
        registerItem();
    }

    @Override
    public boolean matchesName(String searchStr) {
        return title.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return director.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return releaseDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        System.out.println("Movie registered with ID: " + id);

    }

    @Override
    public String toString() {
        return "* " + title + System.lineSeparator() +
                " - Directed by: " + director + System.lineSeparator() +
                " - Released: " + releaseDate + System.lineSeparator() +
                " - Id: " + id;
    }
}
