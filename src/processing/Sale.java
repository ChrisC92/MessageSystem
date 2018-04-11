package processing;

import java.util.Objects;

public class Sale {

    private String productName;
    private int currentPrice;
    private int numberOfSales =1;

    public Sale(String name, int initPrice) {
        productName = name;
        currentPrice = initPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        if(currentPrice >= 0 ) {
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

    @Override
    public String toString(){
        return productName + " at " + currentPrice + "p";
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
