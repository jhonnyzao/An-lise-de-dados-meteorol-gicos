package core;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import mappers.TemperaturaMapper;
import reducers.DesvioPadraoReducer;
import reducers.MediaReducer;

public class Analise {

	public static void main(String[] args) throws IOException {
		JobConf conf = new JobConf(Analise.class);
		conf.setJobName("Análise Metereológica");

		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		String metodo = args[2];

		if (metodo.contains("med")) {
			conf.setReducerClass(MediaReducer.class);
		} else if (metodo.contains("dp")) {
			conf.setReducerClass(DesvioPadraoReducer.class);
		}

		if (metodo.contains("temp")) {
			conf.setMapperClass(TemperaturaMapper.class);
		}
		
		//TODO periodo

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(FloatWritable.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(FloatWritable.class);

		JobClient.runJob(conf);

	}

}
