package com.lendingcatalog.model;


public interface CatalogItem {
    String getType();

    String getName();

    String getCreator();

    String getDate();

    boolean matchesName(String searchStr);
    boolean matchesCreator(String searchStr);
    boolean matchesYear(int searchYear);
    void registerItem();
}
