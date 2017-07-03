package funcoes_estatisticas;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphPlotter {
	static DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	
	
	public static void create(Text key, ArrayList<Float> values) throws Exception {
		for(Float value : values) {
			line_chart_dataset.addValue(value, "", key);
		}	      
	}
	
	public static void plot(){
		JFreeChart lineChartObject = ChartFactory.createLineChart(
				"Media por ano","Ano",
				"Total",
				line_chart_dataset,PlotOrientation.VERTICAL,
				true,true,false);
	     int width = 640;    /* Width of the image */
	     int height = 480;   /* Height of the image */ 
	     File lineChart = new File("LineChart.jpeg"); 
	     try {
			ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
		} catch (IOException e) {
			System.out.println("Erro ao gerar grafico");
			e.printStackTrace();
		}
	}

}
