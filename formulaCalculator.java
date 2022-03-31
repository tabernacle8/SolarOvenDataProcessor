import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public static String calculateAverageTemp(List < Double > tempData) {
        double sum = 0;
        for (Double temp: tempData) {
            sum += temp;
        }
        return String.format("Average Temp: %.2f", sum / tempData.size());
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Max Temp
     */
    public static String calculateMaxTemp(List < Double > tempData) {
        double max = 0;
        for (Double temp: tempData) {
            if (temp > max) {
                max = temp;
            }
        }
        return String.format("Max temp: %.2f", max);
    }

    /**
     * An example of a calculation that can be done on a list of doubles
     * @param tempData
     * @return Min Temp
     */
    public static String calculateMinTemp(List < Double > tempData) {
        double min = tempData.get(0);
        for (Double temp: tempData) {
            if (temp < min) {
                min = temp;
            }
        }
        return String.format("Min temp: %.2f", min);
    }

    /**
     * Calculates efficnency of the solar oven based on dynamic data in efficiency_calculations.csv
     * @param tempData
     * @return Average Temp
     */
    public static String calculateEfficiency(String sourceFile) {
        try {
            Map < String, Double > formulaData = dataImporter.readDataFormatted(sourceFile);
            double energyOut = formulaData.get("density") * formulaData.get("volume") * formulaData.get("sHeatAir") * (formulaData.get("iTemp") - formulaData.get("aTemp"));
            double energyIncident = formulaData.get("insolation") * formulaData.get("sunHours") * formulaData.get("testTime") * formulaData.get("surfaceArea");

            return String.format("Efficiency clculation: %.2f", energyOut / energyIncident);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "================================================================\n"+
        "CRASH DETECTED! SOMEBODY MESSED UP SOMEWHERE. SEE ABOVE LINE FOR MORE INFO"+
        "\n================================================================";

    }



}