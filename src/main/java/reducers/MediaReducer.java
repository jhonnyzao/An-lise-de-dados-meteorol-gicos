package reducers;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import funcoes_estatisticas.Dados;
import funcoes_estatisticas.GraphPlotter;
import funcoes_estatisticas.Media;

public class MediaReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable mediaTemperatura = new FloatWritable();

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, final Context context)
			throws IOException, InterruptedException {

		Dados dados = Media.mediaPasso(values);
		Float resultado = dados.getMedia();

		if (resultado != null) {
			mediaTemperatura.set(resultado);
			context.write(key, mediaTemperatura);
		}

		try {
			GraphPlotter.create(key, dados.getElementos());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
