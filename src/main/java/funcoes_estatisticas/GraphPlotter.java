package funcoes_estatisticas;


import java.io.File;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphPlotter {
	
	public static void create(Text key, Iterable<FloatWritable> values) throws Exception {
	      DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
			for(FloatWritable value : values) {
				line_chart_dataset.addValue(value.get(), "", key);
			}

			JFreeChart lineChartObject = ChartFactory.createLineChart(
					"Media por ano","Ano",
					"Total",
					line_chart_dataset,PlotOrientation.VERTICAL,
					true,true,false);

	      int width = 640;    /* Width of the image */
	      int height = 480;   /* Height of the image */ 
	      File lineChart = new File("LineChart.jpeg"); 
	      ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
	   }

}
