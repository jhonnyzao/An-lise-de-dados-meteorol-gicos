package funcoes_estatisticas;

import java.util.ArrayList;

import java.util.Iterator;

import org.apache.hadoop.io.FloatWritable;

public class Media {

	public static Float media(Iterator<FloatWritable> values) {
		return mediaPasso(values).getMedia();
	}

	public static Dados mediaPasso(Iterator<FloatWritable> values) {
		Dados dados = new Dados();
		float indicadorMedio = 0;
		ArrayList<Float> indicadores = new ArrayList<Float>();

		while (values.hasNext()) {
			float indicador = values.next().get();
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
