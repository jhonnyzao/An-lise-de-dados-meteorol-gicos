package reducers;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import funcoes_estatisticas.MinimosQuadrados;

public class MinimosQuadradosReducer extends Reducer<Text, FloatWritable, Text, DoubleWritable> {

	private DoubleWritable y0 = new DoubleWritable();
	private DoubleWritable y1 = new DoubleWritable();
	
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text key, Iterable<FloatWritable> values, Context ctx) throws IOException, InterruptedException {
		
		MinimosQuadrados min = new MinimosQuadrados(values);
		
		double y0value = min.getA() + min.getB() * min.getXMin();
		double y1value = min.getA() + min.getB() * min.getXMax();
		
		y0.set(y0value);
		y1.set(y1value);
		
		ctx.write(new Text("y0"), y0);
		ctx.write(new Text("y1"), y1);

	}

}