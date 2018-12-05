package classComplexity;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.*;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

public class PlotTheData extends JFrame {
//    private static final long serialVersionUID = 6294689542092367723L;

    public PlotTheData(String title, XYDataset theDataSet) throws IOException {
        super(title);

        // Create dataset
        XYDataset dataset = theDataSet;
        PlotOrientation plotOrientation = PlotOrientation.HORIZONTAL;

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot("WCM vs RFC for all the Classes","Weighted Method Count", "Response Set of a Class", dataset, plotOrientation, true,true,false);


        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));

        // 5x5 red pixel circle
        // changing the shape of the points
        Shape shape  = new Ellipse2D.Double(0,0,5,5);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        XYItemRenderer renderer = xyPlot.getRenderer();

        // set only shape of series with index i
        renderer.setSeriesShape(0, shape);
        renderer.setSeriesPaint(0, Color.blue);


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        try (OutputStream out = new FileOutputStream("MyChart.png")){
            ChartUtilities.writeChartAsPNG(out, chart, 1600,800);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}