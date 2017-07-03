package funcoes_estatisticas;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphPlotter {
	static DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
	static String output = "output";

	public static void create(Text key, float value) throws Exception {
		line_chart_dataset.addValue(value, "", key);

	}

	public static void setOutputFolder(String output) {
		GraphPlotter.output = output;
	}

	public static void plot() {
		JFreeChart lineChartObject = ChartFactory.createLineChart("Media por ano", "Ano", "Total", line_chart_dataset,
				PlotOrientation.VERTICAL, true, true, false);
		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		new File(output).mkdir();
		File lineChart = new File(output + "/LineChart.jpeg");
		try {
			ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
		} catch (IOException e) {
			System.out.println("Erro ao gerar grafico");
			e.printStackTrace();
		}
	}

}
