package output.console;


import inputdata.SampleInputData;
import processing.ProcessInputData;
import java.util.Queue;

public class Main {


    public static void main(String[] args) {
        ProcessInputData processData = new ProcessInputData();
        Queue<String> inputData = SampleInputData.batchModifySales(300);
        processData.addSalesFromQueue(inputData);

    }
}
