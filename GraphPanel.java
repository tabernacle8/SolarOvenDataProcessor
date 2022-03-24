import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Original Author/CoDeveloper - "Hovercraft Full of Eels", "Rodrigo Azevedo"
 * @author Carter Kosturos - The original file has been hevaily modified to fit the needs of this project
 * 
 * 
 * @apiNote This code was retrieved from github and is usable under a common license (MIT)
 * @category Graphing Utility (Offsite)
 *
 * This is an improved version of Hovercraft Full of Eels (https://stackoverflow.com/users/522444/hovercraft-full-of-eels)
 * answer on StackOverflow: https://stackoverflow.com/a/8693635/753012
 *
 * GitHub user @maritaria has made some performance improvements which can be found in the comment section of this Gist.
 */

public class GraphPanel extends JPanel {
    private int padding = 25;
    private int labelPadding = 75;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private int pointWidth = 4;
    private int numberYDivisions = 10;
    private List<Double> tempData;
    private List<Double> timeData;
    private boolean linearRegression = false;


    public static void main(String[] args){
        //Warning you are in debug mode
        System.out.println("========================\n\nWARNING, PROJECT RAN FROM WRONG CLASS FILE (Graph Pannel). Debug mode is on!\n\n========================");
        //Run mainRunner
        try {
            //Not a safe way to do this! Run from the oringinal mainRunner if possible!
            mainRunner.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Graph pannel main helper method
     * @param tempData
     * @param timeData
     * @param linearRegression
     */
    public GraphPanel(List<Double> scores, List<Double> times, boolean linearRegression) {
        this.tempData = scores;
        this.timeData = times;
        this.linearRegression = linearRegression;
    }

    /**
     * Creates a point from raw x/y data
     * @param tempData
     * @param timeData
     */
    public Point createPointFromRaw(int x, int y){

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (tempData.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());


        int x1 = (int) (tempData.size()-1 * xScale + padding + labelPadding);
        int y1 = (int) ((getMaxScore()-y) * yScale + padding);
        return new Point(x1, y1);
    }

    /**
     * Paints graph based on recieved data
     * @param tempData
     * @param timeData
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (tempData.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < tempData.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - tempData.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (tempData.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < tempData.size(); i++) {
            if (tempData.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (tempData.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((tempData.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i+1 + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
        }

        //Add graph x label
        g2.drawString("Time Recorded (Seconds)", getWidth()/2 - 30, getHeight() - 10);

        //Add graph y label. Add Temp and data on two lines
        g2.drawString("Temp", 10, getHeight()/2 - 30);
        g2.drawString("Data (C)", 10, getHeight()/2);

        if(linearRegression){
        //Find line of best fit
        double[] tempDataFormed = dataImporter.convertToPrimitiveList(tempData);
        double[] timeDataFormed = dataImporter.convertToPrimitiveList(timeData);
        LinearRegression regCalc = new LinearRegression(tempDataFormed, timeDataFormed);
        System.out.println(regCalc.predict(tempData.get(tempData.size()-1)));
        

        //Add line of best fit to graph
        //Change color to red and to dashed line
        g2.setStroke(GRAPH_STROKE);
        g2.setColor(Color.RED);
        Point p1 = new Point(graphPoints.get(graphPoints.size()-1).x, graphPoints.get(graphPoints.size()-1).y);
        Point p2 = createPointFromRaw((timeData.get(tempData.size()-1)).intValue(), padding+(int)regCalc.predict(tempData.get(tempData.size()-1)));
        g2.drawLine(p1.x, p1.y, p2.x, p2.y);

        //Reset color back to black
        g2.setColor(Color.BLACK);
        }
        


        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }

        
    }

    /**
     * Gets min score
     * @return the current data
     */
    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : tempData) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }

    /**
     * Gets max score
     * @return the current data
     */
    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : tempData) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    /**
     * Set data point
     * @param scores the data to set
     */
    public void setScores(List<Double> scores) {
        this.tempData = scores;
        invalidate();
        this.repaint();
    }

    /**
     * Scores getter method
     */
    public List<Double> getScores() {
        return tempData;
    }
    
    /**
     * Create and show gui, main driver code
     * @param scores the data to set
     * @param timeData the time data to set
     * @param linearRegression the linear regression information (Experimental)
     */
    static void createAndShowGui(List<Double> scores, List<Double> timeData, boolean linearRegression) {

        GraphPanel mainPanel = new GraphPanel(scores, timeData, linearRegression);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Graphing Panel :)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}