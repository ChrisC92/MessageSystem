package input.products;

import java.util.LinkedList;
import java.util.List;

public abstract class Product {

    private List sale;
    private int value;

    public Product(int initPrice) {
        sale = new LinkedList();
        value = initPrice;
    }


    public void add(Product product) {
        sale.add(product);
    }

    public void setValue(int price ) {
        if(price >= 0 ) {
            value = price;
        }
    }


}
