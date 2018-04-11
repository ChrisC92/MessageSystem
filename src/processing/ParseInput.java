package processing;


import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseInput {

    private List<Sale> sales;
    private List<String> salesRawData;

    public ParseInput() {
        sales = new LinkedList();
        salesRawData = new LinkedList<>();
    }

    public void addSalesFromList(Queue<String> sales) {
        for (String sale : sales) {
            addSale(sale);
        }
    }

    public void addSale(String sale) {
        String firstWordRegex = "([^\\\\\\s]+)";

        String toCheck = extractRegex(sale, firstWordRegex, 0);

        if (toCheck.matches("[0-9]+") && toCheck.length() > 0) {
            createMultiProducts(sale);
        } else if (toCheck.toUpperCase().equals(toCheck)) {
            createAdjustmentProduct(sale);
        } else if (!toCheck.toUpperCase().equals(toCheck)) {
            createSingleProduct(sale);
        } else {
            throw new IllegalArgumentException("Message input is not valid and voided");
        }
    }

    public List getListOfSales() {
        return sales;
    }

    private void createSingleProduct(String saleString) {
        // Regex will take the first characters before first whitespace from the saleString
        String name = extractRegex(saleString, "([^\\\\\\s]+)" , 0);

        // Regex will extract the first digits in saleString
        String price = extractRegex(saleString, "(\\d+)" , 0);
        Sale sale = new Sale(name, Integer.parseInt(price));
        addToList(sale);
        salesRawData.add(saleString);
    }

    // ADD 20p apples
    // SUBTRACT 10p apples
    // MULTIPLY 2 apples
    //TODO: add to raw data list
    private void createAdjustmentProduct(String saleString) {

        // Regex will take the first characters before first whitespace from the saleString
        String modifierType = extractRegex(saleString, "([^\\\\\\s]+)" , 0);

        if (modifierType.equals("ADD") || modifierType.equals("SUBTRACT")) {
            String modifierNum = extractRegex(saleString, "(\\d+[p])" , 0);
            int amount = Integer.parseInt(modifierNum = removeLastChar(modifierNum));
            String productName = extractRegex(saleString, "\\s(\\w+)$" , 0);
            productName = removeFirstChar(productName);
            modifySales(productName, amount, modifierType);

        } else if (modifierType.equals("MULTIPLY")) {
            String modifierNum = extractRegex(saleString, "(\\d+)", 0);
            int amount = Integer.parseInt(modifierNum);
            String productName = extractRegex(saleString, "\\s(\\w+)$" , 0);
            productName = removeFirstChar(productName);
            modifySales(productName, amount, modifierType);

        } else {
            throw new IllegalArgumentException("Modifier is not valid and sale message voided");
        }
    }

    private void createMultiProducts(String saleString) {
        // Regex will take the first characters before first whitespace from the saleString
        int numOfSales = Integer.parseInt(extractRegex(saleString, "([^\\\\\\s]+)" , 0));

        // Regex will take the second group of characters after the first whitespace
        String name = extractRegex(saleString, "^\\S*\\s+(\\S+)" , 1);
        name = removeLastChar(name);

        // Regex takes the digits attached with a p which will be the price in pence
        String priceString = extractRegex(saleString, "(\\d+[p])" , 0);
        priceString = removeLastChar(priceString);
        int price = Integer.parseInt(priceString);
        for (int i = 0; i < numOfSales; i++) {
            Sale sale = new Sale(name, price);
            addToList(sale);
            salesRawData.add(saleString);
        }
    }

    private String extractRegex(String input, String regex, int index) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(index);
        }
        return "regex not found";
    }

    private void modifySales(String product, int amount, String modifier) {
        if (!sales.isEmpty()) {
            for(Sale sale : sales) {
                if(sale.getName().equals(product)) {
                    modifyProduct(sale, modifier, amount);
                }
            }
        }
    }
    // TODO: more thorough error checking
    private void modifyProduct(Sale sale, String modifier, int amount) {
        if(modifier.equals("ADD")) {
            int newPrice = sale.getCurrrentPrice() + amount;
            sale.setCurrentPrice(newPrice);
        } else if(modifier.equals("SUBTRACT")) {
            int newPrice = sale.getCurrrentPrice() - amount;
            if(newPrice >= 0) {
                sale.setCurrentPrice(newPrice);
            }
        } else {
            if(amount > 0){
                int newPrice = sale.getCurrrentPrice() * amount;
                sale.setCurrentPrice(newPrice);
            }

        }
    }

    private void addToList(Sale sale) {
        boolean isInList = false;
        if (sales.isEmpty()) {
            sales.add(sale);
        } else {
            for (Sale inList : sales) {
                if (inList.equals(sale)) {
                    inList.newSale();
                    isInList = true;
                    break;
                }
            }
            if (!isInList) {
                sales.add(sale);
            }
        }
    }

    private String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }
    private String removeFirstChar(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        return s.substring(1, s.length());
    }
}
