package com.lendingcatalog.model;

public class BasicCatalogItem implements CatalogItem {
    private String type;
    private String name;
    private String creator;
    private String date;

    public BasicCatalogItem(String type, String name, String creator) {
        this.type = type;
        this.name = name;
        this.creator = creator;
        this.date = date;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s: %s by %s (%s)", type, name, creator, date);
    }

    @Override
    public boolean matchesName(String searchName) {
        return name.equalsIgnoreCase(searchName);
    }

    @Override
    public boolean matchesCreator(String searchCreator) {
        return creator.equalsIgnoreCase(searchCreator);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return date.startsWith(Integer.toString(searchYear));
    }

    @Override
    public void registerItem() {

    }
}
