package mappers;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CondensacaoDaAguaMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, FloatWritable> {

	final static double MISSING = 9999.9;

	public void map(LongWritable key, Text value, OutputCollector<Text, FloatWritable> output, Reporter reporter)
			throws IOException {
		String linha = value.toString();

		if (linha.contains("STN")) {
			return;
		}

		String ano = linha.substring(14, 18);

		float pontoMedio;

		pontoMedio = Float.parseFloat(linha.substring(36, 42));

		if (pontoMedio != MISSING) {
			output.collect(new Text(ano), new FloatWritable(pontoMedio));
		}

	}

}
