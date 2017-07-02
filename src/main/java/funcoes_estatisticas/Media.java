package funcoes_estatisticas;

import java.util.ArrayList;

import org.apache.hadoop.io.FloatWritable;

public class Media {

	public static Float media(Iterable<FloatWritable> values) {
		return mediaPasso(values).getMedia();
	}

	public static Dados mediaPasso(Iterable<FloatWritable> values) {
		Dados dados = new Dados();
		float indicadorMedio = 0;
		ArrayList<Float> indicadores = new ArrayList<Float>();

		for(FloatWritable value : values) {
			float indicador = value.get();
			indicadores.add(indicador);
			indicadorMedio += indicador;
		}

		if (indicadores.size() >= 1) {
			indicadorMedio /= indicadores.size();
			dados.setElementos(indicadores);
			dados.setMedia(indicadorMedio);
		}
		return dados;
	}

}
