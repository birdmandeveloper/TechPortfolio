package com.lendingcatalog.model;

import java.util.UUID;

public abstract class Tool implements CatalogItem {
    private String id;
    private String name;
    private String manufacturer;
    private int quantity;

    public Tool(String name, String manufacturer, int quantity) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.id = UUID.randomUUID().toString();
        registerItem();
    }

    @Override
    public boolean matchesName(String searchStr) {
        return name.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false; // Tools don't have a year
    }

    @Override
    public void registerItem() {
        System.out.println("Tool registered with ID: " + id);
        // Log the creation of the tool
    }

    @Override
    public String toString() {
        return "* " + name + System.lineSeparator() +
                " - Manufacturer: " + manufacturer + System.lineSeparator() +
                " - Quantity: " + quantity + System.lineSeparator() +
                " - Id: " + id;
    }
}
