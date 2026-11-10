package com.restaurant.catalog;

import com.restaurant.catalog.factory.MenuItemFactory;
import com.restaurant.catalog.model.MenuItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemFactoryTest {

    @Test
    void testCreateMenuItemCategories() {
        MenuItem burger = MenuItemFactory.createMenuItem("1", "food", "Cheeseburger", 8.99);
        assertEquals("Food", burger.getCategory());

        MenuItem drink = MenuItemFactory.createMenuItem("2", "beverage", "Cola", 2.50);
        assertEquals("Drink", drink.getCategory());

        MenuItem dessert = MenuItemFactory.createMenuItem("3", "dessert", "Ice Cream", 4.00);
        assertEquals("Dessert", dessert.getCategory());

        MenuItem unknown = MenuItemFactory.createMenuItem("4", "other", "Item", 5.00);
        assertEquals("Other", unknown.getCategory());
    }
}