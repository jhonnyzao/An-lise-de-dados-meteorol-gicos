package mappers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DefaultMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{
	final static double MISSING = 9999.9;
	
	public static String obtemPosicaoDoMetodo(String metodo){
		Map<String, String> posicoes = new HashMap<String, String>();
		posicoes.put("TEMP", "25-31");
		posicoes.put("DEWP", "36-41");
		posicoes.put("SLP", "47-52");
		posicoes.put("STP", "58-63");
		posicoes.put("VISIB", "69-73");
		posicoes.put("WDSP", "79-83");
		posicoes.put("MXSPD", "89-93");
		posicoes.put("GUST", "96-100");
		posicoes.put("MAX", "103-108");
		posicoes.put("MIN", "111-116");
		posicoes.put("PRCP", "119-123");
		posicoes.put("SNDP", "126-130");
		posicoes.put("FRSHTT", "133-138");
		
		return posicoes.get(metodo);
	}
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		String metodo = conf.get("metodo");
		
		String linha = value.toString();

		if (linha.contains("STN")) {
			return;
		}

		String data = linha.substring(14, 18);

		float valorMedio;

		String posicoesInformacao = obtemPosicaoDoMetodo(metodo);
		String[] posicaoInicialEFinal = posicoesInformacao.split("-");
		
		int posicaoInicial = Integer.parseInt(posicaoInicialEFinal[0]);
		int posicaoFinal = Integer.parseInt(posicaoInicialEFinal[1]);
		
		valorMedio = Float.parseFloat(linha.substring(posicaoInicial, posicaoFinal));

		if (valorMedio != MISSING) {
			context.write(new Text(data), new FloatWritable(valorMedio));
		}

	}
}
