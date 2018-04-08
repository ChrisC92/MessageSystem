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

        String toCheck = extractRegex(sale, firstWordRegex);

        if (toCheck.matches("[0-9]+") && toCheck.length() > 0) {
            createNumericProduct(sale);
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

    private void createSingleProduct(String sale) {
        Sale product = getProductInfo(sale);
        addToList(product);
    }

    private void createAdjustmentProduct(String sale) {
    }

    private void createNumericProduct(String sale) {
    }

    private String extractRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "regex not found";
    }

    private Sale getProductInfo(String sale) {
        String name = extractRegex(sale, "([^\\\\\\s]+)");
        String price = extractRegex(sale, "(\\d+)");
        return new Sale(name, Integer.parseInt(price));
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
}
