package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class AppTest {

    @Test
    public void loadMap() {
        App app = new App();
        app.loadMap();
        Map<String, List<String>> authorBooks = app.getAuthorBooks();

        Assert.assertEquals(19, authorBooks.size());

        List<String> books = authorBooks.get("Kurt Vonnegut");
        Assert.assertEquals(6, books.size());
        Assert.assertTrue(books.contains("Galapagos"));

        books = authorBooks.get("Robert A. Heinlein");
        Assert.assertEquals(2, books.size());
        Assert.assertTrue(books.contains("Stranger in a Strange Land"));

        books = authorBooks.get("Terry Pratchett");
        Assert.assertEquals(3, books.size());
        Assert.assertTrue(books.contains("Going Postal"));

        books = authorBooks.get("Martin Erwig");
        Assert.assertEquals(1, books.size());
        Assert.assertTrue(books.contains("Once Upon an Algorithm"));
    }
}