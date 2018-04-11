package processing;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Sale {

    private String productName;
    private int currentPrice;
    private int numberOfSales = 1;
    private List<String> adjustments;

    public Sale(String name, int initPrice) {
        productName = name;
        currentPrice = initPrice;
        adjustments = new LinkedList<>();
    }

    public void setCurrentPrice(int currentPrice) {
        if (currentPrice >= 0) {
            this.currentPrice = currentPrice;
        }
    }

    public String getName() {
        return productName;
    }

    public int getCurrrentPrice() {
        return currentPrice;
    }

    public int getTotalCost() {
        return currentPrice * numberOfSales;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public void newSale() {
        numberOfSales++;
    }

    public void addToAdjustments(String adjustment) {
        adjustments.add(adjustment);
    }

    public void printAdjustments() {
        if (adjustments.isEmpty()) {
            System.out.println("No adjustments have been made to " + productName);
        } else {
            System.out.println("Adjustments made to - " + productName);
            for (String adjustment : adjustments) {
                System.out.println(adjustment);
            }
        }
    }

    @Override
    public String toString() {
        return numberOfSales + " " + productName + "'s\nTotal cost: " + getTotalCost() + "p";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(productName, sale.productName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productName);
    }
}
