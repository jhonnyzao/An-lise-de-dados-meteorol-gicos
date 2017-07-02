package funcoes_estatisticas;

import org.apache.hadoop.io.FloatWritable;

public class DesvioPadrao {

	public static Dados varianciaPasso(Iterable<FloatWritable> values) {
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

	public static float variancia(Iterable<FloatWritable> values) {
		return varianciaPasso(values).getVariancia();
	}

	public static Dados desvioPadraoPasso(Iterable<FloatWritable> values) {
		Dados dados = varianciaPasso(values);
		if (dados.getMedia() == null) {
			return dados;
		}
		double desvioPadrao = Math.sqrt(dados.getVariancia());
		dados.setDesvioPadrao((float) desvioPadrao);
		return dados;
	}

	public static float desvioPadrao(Iterable<FloatWritable> values) {
		return desvioPadraoPasso(values).getDesvioPadrao();
	}

}
