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

public class TemperaturaMinimosQuadradosReducer implements Reducer<Text, FloatWritable, Text, DoubleWritable> {

	public void configure(JobConf arg0) {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void reduce(Text arg0, Iterator<FloatWritable> arg1, OutputCollector<Text, DoubleWritable> arg2,
			Reporter arg3) throws IOException {

		// TODO

	}

}
