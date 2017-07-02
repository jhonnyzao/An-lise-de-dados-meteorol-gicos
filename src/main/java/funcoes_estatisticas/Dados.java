package funcoes_estatisticas;

import java.util.ArrayList;

public class Dados {

	private ArrayList<Float> elementos;
	private Float media = null;
	private Float desvioPadrao = null;
	private Float variancia = null;

	public Float getVariancia() {
		return variancia;
	}

	public void setVariancia(float variancia) {
		this.variancia = new Float(variancia);
	}

	public ArrayList<Float> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Float> elementos) {
		this.elementos = elementos;
	}

	public Float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = new Float(media);
	}

	public Float getDesvioPadrao() {
		return desvioPadrao;
	}

	public void setDesvioPadrao(float desvioPadrao) {
		this.desvioPadrao = new Float(desvioPadrao);
	}

}
