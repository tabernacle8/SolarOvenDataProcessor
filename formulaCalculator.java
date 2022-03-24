import java.util.List;

public class formulaCalculator {
    
/**
 * @author Carter Kosturos
 * @author Emily Burr
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
        for (Double temp : tempData) {
            sum += temp;
        }
        return String.format("%.2f", sum / tempData.size());
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Max Temp
     */
    public static String calculateMaxTemp(List<Double> tempData) {
        double max = 0;
        for (Double temp : tempData) {
            if (temp > max) {
                max = temp;
            }
        }
        return String.format("%.2f", max);
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Min Temp
     */
    public static String calculateMinTemp(List<Double> tempData) {
        double min = tempData.get(0);
        for (Double temp : tempData) {
            if (temp < min) {
                min = temp;
            }
        }
        return String.format("%.2f", min);
    }

    

}
