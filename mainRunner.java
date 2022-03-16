import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

/**
 * @author Carter Kosturos
 * 
 * Main runner to get data, display graph, and do any calculations necessary
 * 
 * @version 1.0.0
 */
public class mainRunner {

    public static final String DATAFILE = "example_data.csv";

    public static void main(String[] args) throws IOException {

        //Init data
        List < Double > tempData = dataImporter.readData(DATAFILE);
        List < Double > timeData = dataImporter.getTimes(DATAFILE);

        //Run some calculations with the numbers we got
        String averageTemp = formulaCalculator.calculateAverageTemp(tempData);
        System.out.println(averageTemp);

        String maxTemp = formulaCalculator.calculateMaxTemp(tempData);
        System.out.println(maxTemp);

        String minTemp = formulaCalculator.calculateMinTemp(tempData);
        System.out.println(minTemp);

        //Find line of best fit
        double[] tempDataFormed = dataImporter.convertToPrimitiveList(tempData);
        double[] timeDataFormed = dataImporter.convertToPrimitiveList(timeData);
        LinearRegression regCalc = new LinearRegression(tempDataFormed, timeDataFormed);
        System.out.println("Linear Regression Data:"+"\n"+regCalc.toString());

        //Graph data
        //TODO: Add linear regression line to graph
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GraphPanel.createAndShowGui(tempData);
            }
        });

    }
}