import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class dataImporter {

    public static List<Integer> readData() throws IOException { 

        String file = "example_data.csv";
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
}