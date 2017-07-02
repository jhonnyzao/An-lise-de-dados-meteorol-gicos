package reducers;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import funcoes_estatisticas.Media;

public class MediaReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	public void close() throws IOException {
		// TODO Auto-generated method stub
	}

	public void reduce(Text key, Iterator<FloatWritable> values, Context context) throws IOException, InterruptedException {
		Float temperaturaMedia = Media.media(values);

		if (temperaturaMedia != null) {
			context.write(key, new FloatWritable(temperaturaMedia));
		}

	}

}
