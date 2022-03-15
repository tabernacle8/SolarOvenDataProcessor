import java.util.List;

public class formulaCalculator {
    
/**
 * @author Carter Kosturos
 * 
 * A supplementary file to run calculations on acquired data
 * 
 * @version 1.0.0
 */

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Average Temp
     */
    public static String calculateAverageTemp(List<Double> tempData) {
        double sum = 0;
        for (Double data : tempData) {
            sum += data;
        }
        double average = sum / tempData.size();
        return("Average temperature: " + average);
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Max Temp
     */
    public static String calculateMaxTemp(List<Double> tempData) {
        double max = tempData.get(0);
        for (Double data : tempData) {
            if (data > max) {
                max = data;
            }
        }
        return("Max temperature: " + max);
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Min Temp
     */
    public static String calculateMinTemp(List<Double> tempData) {
        double min = tempData.get(0);
        for (Double data : tempData) {
            if (data < min) {
                min = data;
            }
        }
        return("Min temperature: " + min);
    }

    

}
