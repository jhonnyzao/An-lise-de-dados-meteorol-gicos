package funcoes_estatisticas;

import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;

public class DesvioPadrao {

	public static Dados varianciaPasso(Iterator<FloatWritable> values) {
		Dados dados = Media.mediaPasso(values);
		if (dados.getMedia() == null) {
			return dados;
		}

		float variancia = 0;

		for (Float indicador : dados.getElementos()) {
			float valor = indicador - dados.getMedia();
			valor *= valor;
			variancia += valor;
		}

		variancia /= (dados.getElementos().size() - 1);

		dados.setVariancia(variancia);

		return dados;
	}

	public static float variancia(Iterator<FloatWritable> values) {
		return varianciaPasso(values).getVariancia();
	}

	public static Dados desvioPadraoPasso(Iterator<FloatWritable> values) {
		Dados dados = Media.mediaPasso(values);
		if (dados.getMedia() == null) {
			return dados;
		}
		float desvioPadrao = (float) Math.sqrt(dados.getVariancia());
		dados.setDesvioPadrao(desvioPadrao);
		return dados;
	}

	public static float desvioPadrao(Iterator<FloatWritable> values) {
		return desvioPadraoPasso(values).getDesvioPadrao();
	}

}
