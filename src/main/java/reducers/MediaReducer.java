package reducers;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import funcoes_estatisticas.Media;

public class MediaReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	private FloatWritable mediaTemperatura = new FloatWritable();

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, final Context context) throws IOException, InterruptedException {
		Float resultado = Media.media(values);
		Configuration conf = context.getConfiguration();
		String agrupamento = conf.get("agrupamento");

		if (resultado != null) {
			mediaTemperatura.set(resultado);
			context.write(key, mediaTemperatura);
		}

	}

}
