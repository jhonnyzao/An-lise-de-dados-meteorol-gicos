package reducers;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import funcoes_estatisticas.Media;

public class MediaReducer implements Reducer<Text, FloatWritable, Text, FloatWritable> {

	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, FloatWritable> output,
			Reporter reporter) throws IOException {

		Float temperaturaMedia = Media.media(values);

		if (temperaturaMedia != null) {
			output.collect(key, new FloatWritable(temperaturaMedia));
		}

	}

}
