import inputdata.SampleInputData;
import org.junit.Test;
import processing.ProcessInputData;
import processing.Sale;

import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

/**
 *  Uses set test data and checks that printed messages at every 10th item and every 50th item
 *  is correct
 *  For the sake of testing keep the number of sales dividable by 3
 */
public class OutputTests {

    @Test
    public void singularTest() {
        ProcessInputData processInputData60 = new ProcessInputData();
        Queue<String> orders60 = SampleInputData.batchSingleSales(60);
        Queue<String> orders3000 = SampleInputData.batchSingleSales(3000);

        processInputData60.addSalesFromQueue(orders60);
        List<Sale> sales60 = processInputData60.getListOfSales();
        Sale apples60 = sales60.get(0);
        Sale pears60 = sales60.get(1);
        Sale bananas60 = sales60.get(2);

        assertEquals(20, apples60.getNumberOfSales());
        assertEquals(20, pears60.getNumberOfSales());
        assertEquals(20, bananas60.getNumberOfSales());

        assertEquals("apple", apples60.getName());
        assertEquals("pear", pears60.getName());
        assertEquals("banana", bananas60.getName());

        ProcessInputData processInputData3000 = new ProcessInputData();
        processInputData3000.addSalesFromQueue(orders3000);
        List<Sale> sales3000 = processInputData3000.getListOfSales();
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
        ProcessInputData processInputData = new ProcessInputData();
        Queue<String> orders = SampleInputData.batchMultipleSales(3000);
        processInputData.addSalesFromQueue(orders);
        List<Sale> sales = processInputData.getListOfSales();
        Sale apples = sales.get(0);
        Sale pears = sales.get(1);

        assertEquals(1180, apples.getNumberOfSales());
        assertEquals(1270, pears.getNumberOfSales());
    }

    @Test
    public void modifyTestBasic() {
        ProcessInputData processInputData = new ProcessInputData();
        Queue<String> orders = SampleInputData.batchModifySales(3);
        processInputData.addSalesFromQueue(orders);
        List<Sale> sales = processInputData.getListOfSales();
        Sale apples = sales.get(0);
        Sale pears = sales.get(1);
        Sale bananas = sales.get(2);

        assertEquals(5, pears.getCurrrentPrice());
        assertEquals(10, apples.getCurrrentPrice());
        assertEquals(20, bananas.getCurrrentPrice());

    }

    @Test
    public void modifyTests3000() {
        ProcessInputData processInputData = new ProcessInputData();
        Queue<String> orders = SampleInputData.batchModifySales(3000);
        processInputData.addSalesFromQueue(orders);
        List<Sale> sales = processInputData.getListOfSales();
        Sale apples = sales.get(0);
        Sale pears = sales.get(1);
        Sale bananas = sales.get(2);

        assertEquals(10, apples.getCurrrentPrice());
        assertEquals(5, pears.getCurrrentPrice());
        assertEquals(20, bananas.getCurrrentPrice());


    }



}
