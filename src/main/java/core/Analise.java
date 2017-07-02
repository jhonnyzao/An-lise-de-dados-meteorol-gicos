package core;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import mappers.DefaultMapper;
import reducers.DesvioPadraoReducer;
import reducers.MediaReducer;

public class Analise extends Configured implements Tool{

	public static void main(String[] args) throws Exception {
		Analise analise = new Analise();
		int res = ToolRunner.run(analise, args);
		System.exit(res);
	}
	public int run(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration parametros = new Configuration();
		parametros.setBoolean("mapreduce.input.fileinputformat.input.dir.recursive", true);
		Scanner sc = new Scanner(System.in);

		String[] filtros = new String[5];
		for(int i = 0; i<= filtros.length - 1; i++){
			filtros[i] = null;
		}
		
		System.out.println(
			"----- Bem vindo ao analisador de dados meteorológicos-----\n"+
			"Vamos começar a filtrar quais dados iremos analisar.\n"
		);
		
		pedeTipoDeInformacao();
		filtros[0] = sc.nextLine();
		pedeAnoInicial();
		filtros[1] = sc.nextLine();
		pedeAnoFinal();
		filtros[2] = sc.nextLine();
	
		int anoInicial = Integer.parseInt(filtros[1]);
		int anoFinal = Integer.parseInt(filtros[2]);
		if (anoInicial > anoFinal){
			System.out.println("O ano inicial nao pode ser maior do que o ano final");
			System.exit(0);
		}
		
		pedeOperacaoEstatistica();
		filtros[3] = sc.nextLine();
		pedeFormaAgrupamento();
		filtros[4] = sc.nextLine();
		
		parametros.set("metodo", filtros[0]);
		parametros.set("anoInicial", filtros[1]);
		parametros.set("anoFinal", filtros[2]);
		parametros.set("agrupamento", filtros[4]);
		
		Job job = new Job(parametros, "Análise Metereológica");
		job.setJarByClass(Analise.class);
		job.setMapperClass(DefaultMapper.class);
		
		Path inputFilePath = new Path(args[0]);
		Path outputFilePath = new Path(args[1]);
		
		//apaga diretorio de output se houver, dado que o hdfs nao consegue sobrescreve-lo em tempo de execucao
		FileSystem hdfs = FileSystem.get(parametros);
		if (hdfs.exists(outputFilePath)) {
		    hdfs.delete(outputFilePath, true);
		}
		
		//filtra os arquivos que serao enviados pro inputpath de acordo com os anos escolhidos
		FileInputFormat.setInputPathFilter(job, FiltroAnual.class);
		FileInputFormat.addInputPath(job, inputFilePath);
		FileOutputFormat.setOutputPath(job, outputFilePath);
		
		if (filtros[3].contains("MED")) {
			job.setReducerClass(MediaReducer.class);
		} else if (filtros[3].contains("DESV")) {
			job.setReducerClass(DesvioPadraoReducer.class);
		} else {
			return -1;
		}

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);

		return job.waitForCompletion(true) ? 0 : 1;
	}
	
	public static void pedeTipoDeInformacao(){
		System.out.println(
			"Por favor, digite a sigla correspondente ao tipo de informação desejada:\n\n"+
			"Sigla  | Informação\n\n"+
			"TEMP   | Temperatura\n"+
			"DEWP   | Temperatura de condensação\n"+
			"SLP    | Pressão marítma\n"+
			"STP    | Pressão do ar\n"+
			"VISIB  | Visibilidade do dia\n"+
			"WDSP   | Velocidade do vento\n"+
			"MXSPD  | Velocidade máxima do vento\n"+
			"GUST   | Rajada máxima\n"+
			"MAX    | Temperatura máxima\n"+
			"MIN    | Temperatura mínima\n"+
			"PRCP   | Precipitação total\n"+
			"SNDP   | Profundidade de neve em polegadas\n"+
			"FRSHTT | Em representação binária, indica se houve Fog, Rain, Snow, Hail, Thunder e Tornado, respectivamente\n"
		);
	}
	
	public static void pedeAnoInicial(){
		System.out.println("Você quer que dados de a partir de que ano entrem nos cálculos? Ex: 2009");
	}
	
	public static void pedeAnoFinal(){
		System.out.println("E qual o ano limite? Ex: 2017");
	}
	
	public static void pedeOperacaoEstatistica(){
		System.out.println(
			"Que operação você deseja realizar na análise? Digite a sigla correspondente:\n"+
			"MED  | Média\n"+
			"DESV | Desvio padrão\n"+
			"MIN  | Mínimos quadrados\n"
		);
	}
	
	public static void pedeFormaAgrupamento(){
		System.out.println(
			"E como você quer agrupar os resultados? Digite a sigla da opção desejada:\n\n"+
			"MES | Por mês\n"+
			"SEM | Por dia da semana\n"+
			"ANO | Por ano\n"
		);
	}
}
