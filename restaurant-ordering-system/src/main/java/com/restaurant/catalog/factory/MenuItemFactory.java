package com.restaurant.catalog.factory;

import com.restaurant.catalog.model.MenuItem;

public class MenuItemFactory {

    public static MenuItem createMenuItem(String id, String type, String name, double price) {
        if (type == null) {
            return new MenuItem(id, name, price, "General");
        }

        switch (type.toLowerCase()) {
            case "food":
            case "dish":
                return new MenuItem(id, name, price, "Food");
            case "drink":
            case "beverage":
                return new MenuItem(id, name, price, "Drink");
            case "dessert":
                return new MenuItem(id, name, price, "Dessert");
            default:
                return new MenuItem(id, name, price, "Other");
        }
    }
}