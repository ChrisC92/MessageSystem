package output.messages;

import processing.Sale;

import java.util.List;
import java.util.Queue;

public class ConsoleMessages {


     public static void printMessage10thSale(List<Sale> sales){
         System.out.println("Sales: ");
         for(Sale sale : sales) {
             System.out.println(sale);
         }
         System.out.println("**************");
     }

     public static void printMessage50thSale(List<Sale> sales)  {
         System.out.println("Stopping processing...");
         try {
             Thread.sleep(100);
         } catch(InterruptedException ie) {
             ie.printStackTrace();
         }
         System.out.println("Adjustments to each product sold");
         for(Sale sale : sales) {
             sale.printAdjustments();
         }
     }
}
