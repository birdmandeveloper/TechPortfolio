package com.lendingcatalog;

import com.lendingcatalog.model.CatalogItem;
import com.lendingcatalog.model.BasicCatalogItem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static final String FIELD_DELIMITER = "\\|";
    private static final String FILE_BASE_PATH = "/";
    private final Scanner keyboard = new Scanner(System.in);
    private Map<String, List<CatalogItem>> catalog = new HashMap<>();

    public static void main(String[] args) {
        App app = new App();
        app.initialize();
        app.run();
    }

    private void run() {
        boolean exit = false;

        while (!exit) {
            int choice = promptForMenuSelection(
                    "Menu:\n1. Display Catalog\n2. Search Catalog\n3. Exit\nSelect an option: ");

            switch (choice) {
                case 1:
                    displayCatalog();
                    break;
                case 2:
                    searchCatalog();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayCatalog() {
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            System.out.println("Member: " + entry.getKey());
            List<CatalogItem> items = entry.getValue();
            for (CatalogItem item : items) {
                System.out.println("  " + item.toString());
            }
            System.out.println();
        }
        promptForReturn();
    }

    private void searchCatalog() {
        int searchOption = promptForMenuSelection(
                "Search by:\n1. Name\n2. Creator\n3. Year\nSelect an option: ");

        switch (searchOption) {
            case 1:
                String searchName = promptForString("Enter name to search: ");
                searchByName(searchName);
                break;
            case 2:
                String searchCreator = promptForString("Enter creator to search: ");
                searchByCreator(searchCreator);
                break;
            case 3:
                int searchYear = promptForYear("Enter year to search: ");
                searchByYear(searchYear);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchByName(String searchStr) {
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            String memberName = entry.getKey();
            List<CatalogItem> items = entry.getValue();
            for (CatalogItem item : items) {
                if (item.matchesName(searchStr)) {
                    System.out.println("Member: " + memberName);
                    System.out.println("  " + item.toString());
                }
            }
        }
        promptForReturn();
    }

    private void searchByCreator(String searchStr) {
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            String memberName = entry.getKey();
            List<CatalogItem> items = entry.getValue();
            for (CatalogItem item : items) {
                if (item.matchesCreator(searchStr)) {
                    System.out.println("Member: " + memberName);
                    System.out.println("  " + item.toString());
                }
            }
        }
        promptForReturn();
    }

    private void searchByYear(int searchYear) {
        for (Map.Entry<String, List<CatalogItem>> entry : catalog.entrySet()) {
            String memberName = entry.getKey();
            List<CatalogItem> items = entry.getValue();
            for (CatalogItem item : items) {
                if (item.matchesYear(searchYear)) {
                    System.out.println("Member: " + memberName);
                    System.out.println("  " + item.toString());
                }
            }
        }
        promptForReturn();
    }

    private void initialize() {
        String[] files = {
                "items-bbillingsbottom.dat",
                "items-fbrannon.dat",
                "items-rcagarwal.dat",
                "items-sboyton.dat",
                "items-shestrop.dat",
                "items-smeeks.dat",
                "items-vspivey.dat",
                "members.dat"
        };

        for (String fileName : files) {
            InputStream inputStream = getClass().getResourceAsStream(FILE_BASE_PATH + fileName);
            if (inputStream == null) {
                System.out.println("Resource not found: " + fileName);
                continue;
            }

            Scanner fileScanner = new Scanner(inputStream);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(FIELD_DELIMITER);

                if (fileName.equals("members.dat")) {
                    String memberName = parts[0] + " " + parts[1];
                    String itemFileName = parts[2];
                    CatalogItem item = new BasicCatalogItem(memberName, itemFileName, "0");
                    catalog.computeIfAbsent(memberName, k -> new ArrayList<>()).add(item);
                } else {
                    String itemType = parts[0];
                    String itemName = parts[1];
                    String itemCreator = parts[2];
                    String itemDate = parts[3];
                    CatalogItem item = new BasicCatalogItem(itemType, itemName, itemCreator);
                    catalog.computeIfAbsent(itemCreator, k -> new ArrayList<>()).add(item);
                }
            }
            fileScanner.close();
        }
    }

    private int promptForMenuSelection(String message) {
        System.out.print(message);
        return keyboard.nextInt();
    }

    private String promptForString(String message) {
        System.out.print(message);
        keyboard.nextLine();
        return keyboard.nextLine();
    }

    private int promptForYear(String message) {
        int year;
        do {
            System.out.print(message);
            year = keyboard.nextInt();
        } while (year < 1000 || year > 9999);
        return year;
    }

    private void promptForReturn() {
        System.out.print("Press Enter to return...");
        keyboard.nextLine();
        keyboard.nextLine();
    }
}
