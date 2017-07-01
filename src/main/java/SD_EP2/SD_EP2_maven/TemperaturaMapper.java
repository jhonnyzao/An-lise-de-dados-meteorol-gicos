package SD_EP2.SD_EP2_maven;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class TemperaturaMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	public static final int SEM_TEMPERATURA = 9999;

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {

		String linha = value.toString();
		String ano = linha.substring(15, 19);

		int temperaturaDoAR;

		try {
			if (linha.charAt(87) == '+') {
				temperaturaDoAR = Integer.parseInt(linha.substring(88, 92));
			} else {
				temperaturaDoAR = Integer.parseInt(linha.substring(87, 92));
			}

			if (temperaturaDoAR != SEM_TEMPERATURA) {
				output.collect(new Text(ano), new IntWritable(temperaturaDoAR));
			}

		} catch (Exception ex) {

		}

	}

}
