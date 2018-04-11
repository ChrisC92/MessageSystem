package inputdata;


import java.util.LinkedList;
import java.util.Queue;

public class SampleInputData {


    public static Queue batchSingleSales(int NUMBER_OF_SALES) {
        NUMBER_OF_SALES = NUMBER_OF_SALES / 3;
        String orderApple1 = singularSale("apple" , 5);
        String orderPear1 = singularSale("pear" , 10);
        String orderBanana1 = singularSale("banana" , 15);
        Queue listOfSales = new LinkedList();
        for (int i = 0; i < NUMBER_OF_SALES; i++) {
            listOfSales.add(orderApple1);
            listOfSales.add(orderPear1);
            listOfSales.add(orderBanana1);
        }
        return listOfSales;
    }

    public static Queue batchMultipleSales(int NUMBER_OF_SALES) {
        NUMBER_OF_SALES = NUMBER_OF_SALES / 3;
        String orderApple1 = singularSale("apple" , 5);
        String orderPear1 = singularSale("pear" , 10);
        String orderBanana1 = singularSale("banana" , 15);
        Queue listOfSales = new LinkedList();
        for (int i = 0; i < NUMBER_OF_SALES; i++) {
            listOfSales.add(orderApple1);
            listOfSales.add(orderPear1);
            listOfSales.add(orderBanana1);
            if (i % 100 == 0 && i != 0) {
                String add20Apples = multipleSale("apple" , 5, 20);
                String add30pears = multipleSale("pear" , 10, 30);
                listOfSales.add(add20Apples);
                listOfSales.add(add30pears);
            }
        }
        return listOfSales;
    }

    public static Queue batchModifySales(int NUMBER_OF_SALES) {
        NUMBER_OF_SALES = NUMBER_OF_SALES / 3;
        String orderApple1 = singularSale("apple" , 5);
        String orderPear1 = singularSale("pear" , 10);
        String orderBanana1 = singularSale("banana" , 15);
        Queue listOfSales = new LinkedList();
        for (int i = 0; i < NUMBER_OF_SALES; i++) {
            listOfSales.add(orderApple1);
            listOfSales.add(orderPear1);
            listOfSales.add(orderBanana1);
            if (i == 0) {
                String applesTwiceThePrice = adjustPrice("apple" , Adjustment.MULTIPLY, 2);
                String pearsPriceReduce = adjustPrice("pear" , Adjustment.SUBTRACT, 5);
                String bananasAddPrice = adjustPrice("banana" , Adjustment.ADD, 5);
                listOfSales.add(applesTwiceThePrice);
                listOfSales.add(pearsPriceReduce);
                listOfSales.add(bananasAddPrice);
            }
        }
        return listOfSales;
    }

    public enum Adjustment {
        ADD, SUBTRACT, MULTIPLY
    }

    public static String singularSale(String name, int price) {
        return name + " at " + price + "p";
    }

    public static String multipleSale(String name, int price, int amountOfItems) {
        return amountOfItems + " " + name + "s at " + price + "p each";
    }

    public static String adjustPrice(String name, Adjustment adjustment, int amount) {
        if (adjustment.equals(Adjustment.ADD) || adjustment.equals(Adjustment.SUBTRACT)) {
            return adjustment + " " + amount + "p " + name;
        } else{
            return adjustment + " " + amount + " " + name;
        }
    }
}
