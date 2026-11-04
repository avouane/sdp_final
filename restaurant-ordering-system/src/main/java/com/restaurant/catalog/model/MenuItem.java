package com.restaurant.catalog.model;

public class MenuItem {
    private String id;
    private String name;
    private double basePrice;
    private String category;

    public MenuItem(String id, String name, double basePrice, String category) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - $" + basePrice;
    }
}