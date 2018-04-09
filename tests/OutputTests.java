import input.InputData;
import org.junit.Test;
import processing.ParseInput;
import processing.Sale;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *  Uses set test data and checks that printed messages at every 10th item and every 50th item
 *  is correct
 *  For the sake of testing keep the number of sales dividable by 3
 */
public class OutputTests {

    @Test
    public void singularTest() {
        ParseInput parseInput60 = new ParseInput();
        List<String> orders60 = InputData.batchSingleSales(60);
        List<String> orders3000 = InputData.batchSingleSales(3000);

        parseInput60.addSalesFromList(orders60);
        List<Sale> sales60 = parseInput60.getListOfSales();
        Sale apples60 = sales60.get(0);
        Sale pears60 = sales60.get(1);
        Sale bananas60 = sales60.get(2);

        assertEquals(20, apples60.getNumberOfSales());
        assertEquals(20, pears60.getNumberOfSales());
        assertEquals(20, bananas60.getNumberOfSales());

        assertEquals("apple", apples60.getName());
        assertEquals("pear", pears60.getName());
        assertEquals("banana", bananas60.getName());

        ParseInput parseInput3000 = new ParseInput();
        parseInput3000.addSalesFromList(orders3000);
        List<Sale> sales3000 = parseInput3000.getListOfSales();
        Sale apples3000 = sales3000.get(0);
        Sale pears3000 = sales3000.get(1);
        Sale bananas3000 = sales3000.get(2);

        assertEquals(1000, apples3000.getNumberOfSales());
        assertEquals(1000, pears3000.getNumberOfSales());
        assertEquals(1000, bananas3000.getNumberOfSales());

        assertEquals("apple", apples3000.getName());
        assertEquals("pear", pears3000.getName());
        assertEquals("banana", bananas3000.getName());

        assertEquals(5000, apples3000.getTotalCost());
        assertEquals(10000, pears3000.getTotalCost());
        assertEquals(15000, bananas3000.getTotalCost());
    }


    @Test
    public void multipleTest() {
        ParseInput parseInput = new ParseInput();
        List<String> orders = InputData.batchMultipleSales(3000);
        parseInput.addSalesFromList(orders);
        List<Sale> sales = parseInput.getListOfSales();
        Sale apples = sales.get(0);
        Sale pears = sales.get(1);
        Sale bananas = sales.get(2);

        assertEquals(1180, apples.getNumberOfSales());
        assertEquals(1270, pears.getNumberOfSales());
    }

    @Test
    public void modifyTests() {
        ParseInput parseInput = new ParseInput();
        List<String> orders = InputData.batchModifySales(3000);
        parseInput.addSalesFromList(orders);
        List<Sale> sales = parseInput.getListOfSales();
        Sale apples = sales.get(0);
        Sale pears = sales.get(1);
        Sale bananas = sales.get(2);

        assertEquals(10, apples.getCurrrentPrice());
        assertEquals(5, pears.getCurrrentPrice());
        assertEquals(20, bananas.getCurrrentPrice());


    }



}
