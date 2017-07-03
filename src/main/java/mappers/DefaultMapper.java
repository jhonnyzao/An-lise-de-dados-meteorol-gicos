package mappers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DefaultMapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
	final static double MISSING = 9999.9;

	public static String obtemPosicaoDoMetodo(String metodo) {
		Map<String, String> posicoes = new HashMap<String, String>();
		posicoes.put("TEMP", "24-30");
		posicoes.put("DEWP", "35-41");
		posicoes.put("SLP", "46-52");
		posicoes.put("STP", "57-63");
		posicoes.put("VISIB", "68-73");
		posicoes.put("WDSP", "78-83");
		posicoes.put("MXSPD", "88-93");
		posicoes.put("GUST", "95-100");
		posicoes.put("MAX", "102-108");
		posicoes.put("MIN", "110-116");
		posicoes.put("PRCP", "118-123");
		posicoes.put("SNDP", "125-130");
		posicoes.put("FRSHTT", "132-138");

		return posicoes.get(metodo);
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		String metodo = conf.get("metodo");
		String agrupamento = conf.get("agrupamento");

		String linha = value.toString();
		String chave = "";

		if (linha.contains("STN")) {
			return;
		}

		if (agrupamento.contains("ANO")) {
			chave = linha.substring(14, 18);
		} else if (agrupamento.contains("MES")) {
			String mesDia = linha.substring(18, 22);
			String dia = mesDia.substring(mesDia.length() - 2);
			String mes = mesDia.replace(dia, "");
			chave = mes;
			
			//verifica se mês é válido
			try {
				Integer.parseInt(mes);
			} catch (Exception ex) {
				return;
			}
		}

		float valorMedio;

		String posicoesInformacao = obtemPosicaoDoMetodo(metodo);
		String[] posicaoInicialEFinal = posicoesInformacao.split("-");

		int posicaoInicial = Integer.parseInt(posicaoInicialEFinal[0]);
		int posicaoFinal = Integer.parseInt(posicaoInicialEFinal[1]);

		valorMedio = Float.parseFloat(linha.substring(posicaoInicial, posicaoFinal));

		if (valorMedio != MISSING) {
			context.write(new Text(chave), new FloatWritable(valorMedio));
		}

	}
}
