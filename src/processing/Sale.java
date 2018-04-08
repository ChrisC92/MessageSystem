package processing;

import java.util.Objects;

public class Sale {

    private String productName;
    private int price;
    private int numberOfSales =1;

    public Sale(String name, int initPrice) {
        productName = name;
        price = initPrice;
    }

    public void setPrice(int price ) {
        if(price >= 0 ) {
            this.price = price;
        }
    }

    public String getName() {
        return productName;
    }

    public int getSinglePrice() {
        return price;
    }

    public int getTotalCost() {
        return price * numberOfSales;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }
    public void newSale() {
        numberOfSales++;
    }

    @Override
    public String toString(){
        return productName + " at " + price + "p";
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
