package processing;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseInput {

    private List<Sale> sales;

    public ParseInput() {
        sales = new LinkedList();
    }

    public void addSalesFromList(List<String> sales) {
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
            throw new IllegalArgumentException("input is incorrect");
        }
    }

    public List getListOfSales() {
        return sales;
    }

    private void createSingleProduct(String saleString) {
        String name = extractRegex(saleString, "([^\\\\\\s]+)", 0);
        String price = extractRegex(saleString, "(\\d+)", 0);
        Sale sale = new Sale(name, Integer.parseInt(price));
        addToList(sale);
    }

    private void createAdjustmentProduct(String sale) {
    }
    // 10 apples at 10p
    private void createMultiProducts(String saleString) {
        int numOfSales = Integer.parseInt(extractRegex(saleString, "([^\\\\\\s]+)", 0));
        String name = extractRegex(saleString, "^\\S*\\s+(\\S+)", 1);
        name = removeLastChar(name);
        String priceString = extractRegex(saleString, "(\\d+[p])", 0);
        priceString = removeLastChar(priceString);
        int price = Integer.parseInt(priceString);
        for(int i=0; i<numOfSales; i++) {
            Sale sale = new Sale(name, price);
            addToList(sale);
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

    private void addToList(Sale sale) {
        boolean isInList = false;
        if (sales.isEmpty()) {
            sales.add(sale);
        } else {
            for (Sale inList : sales) {
                if(inList.equals(sale)) {
                    inList.newSale();
                    isInList = true;
                    break;
                }
            }
            if(!isInList){
                sales.add(sale);
            }
        }
    }

    private String removeLastChar(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
}
