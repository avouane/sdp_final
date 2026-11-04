package com.restaurant.pricing.strategy;

public class StandardPricing implements PricingStrategy {

    @Override
    public double calculatePrice(double rawAmount) {
        return rawAmount;
    }
}