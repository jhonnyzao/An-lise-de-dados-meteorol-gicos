package SD_EP2.SD_EP2_maven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class TemperaturaReducer implements Reducer<Text, IntWritable, Text, DoubleWritable> {

	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, DoubleWritable> output,
			Reporter reporter) throws IOException {

		double media = 0;
		ArrayList<Integer> temperaturas = new ArrayList<Integer>();

		while (values.hasNext()) {
			int temperatura = values.next().get();
			temperaturas.add(temperatura);
			media += temperaturas.get(temperatura);
		}

		media /= temperaturas.size();

		output.collect(key, new DoubleWritable(media));

	}

}
