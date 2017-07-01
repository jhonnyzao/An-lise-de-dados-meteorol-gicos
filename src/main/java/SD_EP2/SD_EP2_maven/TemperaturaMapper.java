package SD_EP2.SD_EP2_maven;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class TemperaturaMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, FloatWritable> {

	public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter)
			throws IOException {

		String linha = value.toString();

		if (linha.contains("STN")) {
			return;
		}

		String ano = linha.substring(14, 18);

		float temperaturaMedia;

		temperaturaMedia = Float.parseFloat(linha.substring(25, 31));
		output.collect(new Text(ano), new FloatWritable(temperaturaMedia));

	}

}
