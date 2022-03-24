import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carter Kosturos
 * 
 * A simple java applet to retrieve CSV data from a file
 * 
 * @version 1.0.0
 */
public class dataImporter {

    /**
     * Get temp data from csv file
     * @return
     * @throws IOException
     */
    public static List<Double> readData(String file) throws IOException { 

        List<Double> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            while ((line = br.readLine()) != null) {
                
                if(line.startsWith("#")) {
                    continue;
                }

                //TODO: Make work with non second intervals
                content.add(Double.parseDouble(line.split(",")[1]));
            }
            
        } catch (FileNotFoundException e) {
          throw new IOException("File not found");
        }
        
        return content;
    }

    public static List<String> readDataFormatted(String file) throws IOException { 

        List<String> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            while ((line = br.readLine()) != null) {
                
                if(line.startsWith("$")) {
                   content.add(line.split(",")[1]);
                }
                else if(line.startsWith("#")) {
                    continue;
                }
                else{
                    //Throw exception
                    throw new IOException("Malformed heeader! Expected a $/#, got: " + line);
                }
            }
            
        } catch (FileNotFoundException e) {
          throw new IOException("File not found");
        }
        
        return content;
    }

    public static List<Double> getTimes(String file) throws IOException { 

        List<Double> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";

            while ((line = br.readLine()) != null) {
                
                if(line.startsWith("#")) {
                    continue;
                }

                //TODO: Make work with non second intervals
                content.add(Double.parseDouble(line.split(",")[0]));
            }
            
        } catch (FileNotFoundException e) {
          throw new IOException("File not found");
        }
        
        return content;
    }

    public static double[] convertToPrimitiveList(List<Double> originalList){
        double[] formed = new double[originalList.size()];
        for (int i = 0; i < formed.length; i++) {
            formed[i] = originalList.get(i).doubleValue();
        }

        return formed;
    }
}