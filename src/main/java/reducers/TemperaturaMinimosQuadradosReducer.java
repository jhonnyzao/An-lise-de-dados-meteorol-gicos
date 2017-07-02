package reducers;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import funcoes_estatisticas.MinimosQuadrados;

public class TemperaturaMinimosQuadradosReducer implements Reducer<Text, FloatWritable, Text, DoubleWritable> {

	private int eixoX = 1;
	
	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text key, Iterator<FloatWritable> values, OutputCollector<Text, DoubleWritable> output,
			Reporter reporter) throws IOException {
		
		MinimosQuadrados min = new MinimosQuadrados(values);
		double y0 = min.getA() + min.getB() * min.getXMin();
		double y1 = min.getA() + min.getB() * min.getXMax();
		
		output.collect(new Text("y0"), new DoubleWritable(y0));
		output.collect(new Text("y1"), new DoubleWritable(y1));

	}

}
