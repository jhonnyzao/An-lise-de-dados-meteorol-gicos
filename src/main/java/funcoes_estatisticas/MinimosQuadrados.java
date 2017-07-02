package funcoes_estatisticas;

import java.util.Iterator;
import org.apache.hadoop.io.FloatWritable;

public class MinimosQuadrados {
	
	private Iterator<FloatWritable> values;
	private double a, b;
	private float mediaX, mediaY;
	private Dados dados;
	
	public MinimosQuadrados(Iterator<FloatWritable> values) {
		this.values = values;
		dados = Media.mediaPasso(values);
		this.b = calcularB();
		this.a = calcularA();
	}

	public Double calcularB() {
		int x = 1;
		double somatoriaNumerador = 0;
		double somatoriaDenominador = 0;
		mediaY = dados.getMedia();
		
		int n = dados.getElementos().size();
		
		mediaX = (n+1)/2;
		
		while(values.hasNext()) {
			somatoriaNumerador += x * (values.next().get() - mediaY);
			somatoriaDenominador += x * (x - mediaX);
			x++;
		}
		
		return somatoriaNumerador / somatoriaDenominador;
	}
	
	public Double calcularA() {
		return mediaY - b * mediaX;
	}
	
	public double getA() {
		return a;
	}
	
	public double getB() {
		return b;
	}
	
	public double getXMin() {
		return 1;
	}
	
	public double getXMax() {
		return dados.getElementos().size();
	}
	
}
