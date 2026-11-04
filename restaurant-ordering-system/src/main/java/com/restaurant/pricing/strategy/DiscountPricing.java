package com.restaurant.pricing.strategy;

public class DiscountPricing implements PricingStrategy {
    private double discountPercentage;

    public DiscountPricing(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            this.discountPercentage = 0;
        } else {
            this.discountPercentage = discountPercentage;
        }
    }

    @Override
    public double calculatePrice(double rawAmount) {
        return rawAmount * (1 - discountPercentage / 100.0);
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}