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
    public static void main(String[] args) throws IOException {

        List<Double> aquiredData = dataImporter.readData();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               GraphPanel.createAndShowGui(aquiredData);
            }
         });
    }
}
