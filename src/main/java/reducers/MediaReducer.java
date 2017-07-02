package reducers;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.jfree.ui.RefineryUtilities;

import funcoes_estatisticas.GraphPlotter;
import funcoes_estatisticas.Media;

public class MediaReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable mediaTemperatura = new FloatWritable();

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, final Context context) throws IOException, InterruptedException {
		Float resultado = Media.media(values);
		
		if (resultado != null) {
			mediaTemperatura.set(resultado);
			context.write(key, mediaTemperatura);
		}
		

		try {
			GraphPlotter.create(key, values);
		} catch (Exception e) {
			System.out.println("Erro ao gerar gráfico");
			e.printStackTrace();
		}

	}

}
