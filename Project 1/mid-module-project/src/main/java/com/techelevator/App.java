package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class App {

    // The regex string to split the Strings in the dataset.
    private static final String FIELD_DELIMITER = "\\|";

    private static final int TITLE_FIELD = 0;
    private static final int AUTHOR_FIELD = 1;
    private static final int PUBLISHED_YEAR_FIELD = 2;
    private static final int PRICE_FIELD = 3;

    private final Scanner keyboard = new Scanner(System.in);

    private List<String> titles = new ArrayList<>();
    private List<String> authors = new ArrayList<>();
    private List<Integer> publishedYears = new ArrayList<>();
    private List<BigDecimal> prices = new ArrayList<>();

    public static void main(String[] args) {

        App app = new App();
        app.loadData();
        app.run();

    }

    private void loadData() {

        String[] dataset = Dataset.load();

        /*
         Requirement: 1
         Populate the instance variables `titles`, `authors`, `publishedYears`,
         and `prices` by splitting each string in the `dataset` array and adding
         the individual fields to the appropriate list.
         See README for additional details.
         */
        for (String data : dataset) {
            String[] fields = data.split(FIELD_DELIMITER);
            titles.add(fields[TITLE_FIELD]);
            authors.add(fields[AUTHOR_FIELD]);
            publishedYears.add(Integer.parseInt(fields[PUBLISHED_YEAR_FIELD]));
            prices.add(new BigDecimal(fields[PRICE_FIELD]));
        }
    }

    private void run() {

        while (true) {
            // Main menu loop
            printMainMenu();
            int mainMenuSelection = promptForMenuSelection("Please choose an option: ");
            if (mainMenuSelection == 1) {
                // Display data and subsets loop
                while (true) {
                    printDataAndSubsetsMenu();
                    int dataAndSubsetsMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (dataAndSubsetsMenuSelection == 1) {
                        displayDataset(Dataset.load());
                    } else if (dataAndSubsetsMenuSelection == 2) {
                        displayTitlesList(titles);
                    } else if (dataAndSubsetsMenuSelection == 3) {
                        displayAuthorsList(authors);
                    } else if (dataAndSubsetsMenuSelection == 4) {
                        displayPublishedYearsList(publishedYears);
                    } else if (dataAndSubsetsMenuSelection == 5) {
                        displayPricesList(prices);
                    } else if (dataAndSubsetsMenuSelection == 0) {
                        break;
                    }
                }
            }
            else if (mainMenuSelection == 2) {
                while (true) {
                    printSearchBooksMenu();
                    int searchBooksMenuSelection = promptForMenuSelection("Please choose an option: ");
                    if (searchBooksMenuSelection == 1) {
                        // Search by title
                        String filterTitle = promptForString("Enter title: ");
                        /*
                         Requirement: 3b
                         Replace `displayTitlesList(titles)` with calls to the
                         `filterByTitle()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByTitle(filterTitle);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 2) {
                        // Search by author
                        String filterAuthor = promptForString("Enter author: ");
                        /*
                         Requirement: 4b
                         Replace `displayAuthorsList(authors)` with calls to the
                         `filterByAuthor()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByAuthor(filterAuthor);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 3) {
                        // Search by published year
                        int filterYear = promptForPublishedYear("Enter date (YYYY): ");
                        /*
                         Requirement: 5b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYear()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByPublishedYear(filterYear);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 4) {
                        // Search by published year range
                        int filterFromYear = promptForPublishedYear("Enter \"from\" date (YYYY): ");
                        int filterToYear = promptForPublishedYear("Enter \"to\" date (YYYY): ");
                        /*
                         Requirement: 6b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `filterByPublishedYearRange()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByPublishedYearRange(filterFromYear, filterToYear);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 5) {
                        // Find the most recent books
                        /*
                         Requirement: 7b
                         Replace `displayPublishedYearsList(publishedYears)` with calls
                         to the `findMostRecentBooks()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = findMostRecentBooks();
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 6) {
                        // Search by price
                        double filterPrice = promptForPrice("Enter price: ");
                        /*
                         Requirement: 8b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPrice()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByPrice(filterPrice);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 7) {
                        // Search by price range
                        double filterFromPrice= promptForPrice("Enter \"from\" price: ");
                        double filterToPrice = promptForPrice("Enter \"to\" price: ");
                        /*
                         Requirement: 9b
                         Replace `displayPricesList(prices)` with calls to the
                         `filterByPriceRange()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = filterByPriceRange(filterFromPrice, filterToPrice);
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 8) {
                        // Find the least expensive books
                        /*
                         Requirement: 10b
                         Replace `displayPricesList(prices)` with calls to the
                         `findLeastExpensiveBooks()` and `displaySearchResults()` methods.
                         */
                        List<Integer> searchResults = findLeastExpensiveBooks();
                        displaySearchResults(searchResults);
                    } else if (searchBooksMenuSelection == 0) {
                        break;
                    }
                }
            } else if (mainMenuSelection == 0) {
                break;
            }
        }

    }

    /*
     Requirement: 2
     Write the displaySearchResults(List<Integer> indexes) method.
     See README for additional details.
     */
    private void displaySearchResults(List<Integer> indexes) {
        System.out.println("Search Results");
        System.out.println("--------------");
        for (int index : indexes) {
            String title = titles.get(index);
            String author = authors.get(index);
            int publishedYear = publishedYears.get(index);
            BigDecimal price = prices.get(index);
            System.out.printf("%s by %s (%d) - $%.2f\n", title, author, publishedYear, price);
        }
        System.out.println();
    }

    /*
     Requirement: 3a
     Write the filterByTitle(String filter) method.
     See README for additional details.
     */
    private List<Integer> filterByTitle(String filter) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).toLowerCase().contains(filter.toLowerCase())) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 4a
     Write the filterByAuthor(String filter) method.
     See README for additional details.
     */
    private List<Integer> filterByAuthor(String filter) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).toLowerCase().contains(filter.toLowerCase())) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 5a
     Write the filterByPublishedYear(int filter) method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYear(int filter) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == filter) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 6a
     Write the filterByPublishedYearRange(int fromYear, int toYear) method.
     See README for additional details.
     */
    private List<Integer> filterByPublishedYearRange(int fromYear, int toYear) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < publishedYears.size(); i++) {
            int publishedYear = publishedYears.get(i);
            if (publishedYear >= fromYear && publishedYear <= toYear) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 7a
     Write the findMostRecentBooks() method.
     See README for additional details.
     */
    private List<Integer> findMostRecentBooks() {
        List<Integer> searchResults = new ArrayList<>();
        int maxYear = Collections.max(publishedYears);
        for (int i = 0; i < publishedYears.size(); i++) {
            if (publishedYears.get(i) == maxYear) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 8a
     Write the filterByPrice(double filter) method.
     See README for additional details.
     */
    private List<Integer> filterByPrice(double filter) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).doubleValue() == filter) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 9a
     Write the filterByPriceRange(double fromPrice, double toPrice) method.
     See README for additional details.
     */
    private List<Integer> filterByPriceRange(double fromPrice, double toPrice) {
        List<Integer> searchResults = new ArrayList<>();
        for (int i = 0; i < prices.size(); i++) {
            BigDecimal price = prices.get(i);
            if (price.doubleValue() >= fromPrice && price.doubleValue() <= toPrice) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    /*
     Requirement: 10a
     Write the findLeastExpensiveBooks() method.
     See README for additional details.
     */
    private List<Integer> findLeastExpensiveBooks() {
        List<Integer> searchResults = new ArrayList<>();
        BigDecimal minPrice = Collections.min(prices);
        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i).equals(minPrice)) {
                searchResults.add(i);
            }
        }
        return searchResults;
    }

    private void printMainMenu() {
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1. Display Books");
        System.out.println("2. Search for a Book");
        System.out.println("0. Exit");
        System.out.println();
    }

    private void printDataAndSubsetsMenu() {
        System.out.println("Data and Subsets Menu");
        System.out.println("---------------------");
        System.out.println("1. Display All Books");
        System.out.println("2. Display Titles");
        System.out.println("3. Display Authors");
        System.out.println("4. Display Published Years");
        System.out.println("5. Display Prices");
        System.out.println("0. Return to Main Menu");
        System.out.println();
    }

    private void printSearchBooksMenu() {
        System.out.println("Search Books Menu");
        System.out.println("-----------------");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.println("3. Search by Published Year");
        System.out.println("4. Search by Published Year Range");
        System.out.println("5. Find Most Recent Books");
        System.out.println("6. Search by Price");
        System.out.println("7. Search by Price Range");
        System.out.println("8. Find Least Expensive Books");
        System.out.println("0. Return to Main Menu");
        System.out.println();
    }

    private void displayDataset(String[] dataset) {
        System.out.println("Dataset");
        System.out.println("-------");
        for (String data : dataset) {
            System.out.println(data);
        }
        System.out.println();
    }

    private void displayTitlesList(List<String> titles) {
        System.out.println("Titles");
        System.out.println("------");
        for (String title : titles) {
            System.out.println(title);
        }
        System.out.println();
    }

    private void displayAuthorsList(List<String> authors) {
        System.out.println("Authors");
        System.out.println("-------");
        for (String author : authors) {
            System.out.println(author);
        }
        System.out.println();
    }

    private void displayPublishedYearsList(List<Integer> publishedYears) {
        System.out.println("Published Years");
        System.out.println("---------------");
        for (int year : publishedYears) {
            System.out.println(year);
        }
        System.out.println();
    }

    private void displayPricesList(List<BigDecimal> prices) {
        System.out.println("Prices");
        System.out.println("------");
        for (BigDecimal price : prices) {
            System.out.println(price);
        }
        System.out.println();
    }

    private int promptForMenuSelection(String message) {
        int selection = -1;
        while (selection < 0 || selection > 2) {
            try {
                System.out.print(message);
                selection = Integer.parseInt(keyboard.nextLine());
                if (selection < 0 || selection > 2) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        return selection;
    }

    private String promptForString(String message) {
        System.out.print(message);
        return keyboard.nextLine();
    }

    private int promptForPublishedYear(String message) {
        int year = -1;
        while (year < 0) {
            try {
                System.out.print(message);
                year = Integer.parseInt(keyboard.nextLine());
                if (year < 0) {
                    System.out.println("Invalid year. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please try again.");
            }
        }
        return year;
    }

    private double promptForPrice(String message) {
        double price = -1;
        while (price < 0) {
            try {
                System.out.print(message);
                price = Double.parseDouble(keyboard.nextLine());
                if (price < 0) {
                    System.out.println("Invalid price. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Please try again.");
            }
        }
        return price;
    }

}
