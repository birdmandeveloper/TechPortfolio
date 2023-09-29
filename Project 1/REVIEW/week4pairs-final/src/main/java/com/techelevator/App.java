package com.techelevator;

import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;

    private Map<String, List<String >> authorBooks = new HashMap<>();

    public static void main(String[] args) {

        App app = new App();

        app.loadMap();
        app.runNew();
    }

    public void loadMap() {
        String[] dataset = Dataset.load();

        for (String data : dataset) {
            String[] parts = data.split(FIELD_DELIMITER);

            String author = parts[AUTHOR_FIELD];

            List<String> books;
            if (authorBooks.containsKey(author)) {
                books = authorBooks.get(author);
            } else {
                books = new ArrayList<>();
                authorBooks.put(author, books);
            }

            books.add(parts[TITLE_FIELD]);
        }
    }

    public Map<String, List<String>> getAuthorBooks() {
        return authorBooks;
    }

//    private void runNew() {
//
//        for (Map.Entry<String, List<String>> entry : authorBooks.entrySet()) {
//            String author = entry.getKey();
//            List<String> books = entry.getValue();
//
//            System.out.println("========================================");
//
//            System.out.println("Books by " + author);
//
//            for( int i = 0; i < books.size(); ++i) {
//                System.out.println("    " + ( i + 1 ) + ") " +  books.get(i));
//            }
//        }
//    }

    private void runNew() {

        for (Map.Entry<String, List<String>> entry : authorBooks.entrySet()) {
            String author = entry.getKey();
            List<String> books = entry.getValue();

            System.out.println("===");
            System.out.println("Books by " + author);

            for( int i = 0; i < books.size(); ++i) {
                System.out.println(books.get(i));
            }
        }
    }
}
