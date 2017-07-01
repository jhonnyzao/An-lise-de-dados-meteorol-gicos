package SD_EP2.SD_EP2_maven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class TemperaturaReducer implements Reducer<Text, FloatWritable, Text, DoubleWritable> {

	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, DoubleWritable> output,
			Reporter reporter) throws IOException {

		float temperaturaMedia = 0;
		ArrayList<Float> temperaturas = new ArrayList<Float>();

		while (values.hasNext()) {
			float temperatura = values.next().get();
			temperaturas.add(temperatura);
			temperaturaMedia += temperatura;
		}

		if (temperaturas.size() >= 1) {
			temperaturaMedia /= temperaturas.size();

			double variancia = 0;
			double desvioPadrao = 0;

			for (Float temperatura : temperaturas) {
				double valor = temperatura - temperaturaMedia;
				valor *= valor;
				variancia += valor;
			}

			variancia /= (temperaturas.size() - 1);

			desvioPadrao = Math.sqrt(variancia);

			output.collect(key, new DoubleWritable(temperaturaMedia));
			output.collect(key, new DoubleWritable(desvioPadrao));

		}

	}

}
