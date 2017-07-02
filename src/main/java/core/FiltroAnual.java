package core;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class FiltroAnual extends Configured implements PathFilter {
    private String anoInicial;
    private String anoFinal;
    private FileSystem fs;

	@SuppressWarnings("deprecation")
	@Override
	public boolean accept(Path path) {
        try {
            if (fs.isDirectory(path)) {
                return true;
            } else {
            	Boolean result = false;
            	int inicio = Integer.parseInt(this.anoInicial);
            	int fim = Integer.parseInt(this.anoFinal);
            	
            	int anoAux = inicio;
            	
            	for(int i = 0; i <= fim-inicio; i++){
            		String anoAuxTexto = "/" + String.valueOf(anoAux) + "/";
            		if (path.toString().contains(anoAuxTexto)){
            			result = true;
            		}
            		anoAux++;
            	}
            	
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
 
    }

    @Override
	public void setConf(Configuration conf) {
        if (null != conf) {            
        	this.anoInicial = conf.get("anoInicial");
        	this.anoFinal = conf.get("anoFinal");
            try {
                this.fs = FileSystem.get(conf);
            } catch (IOException e) {}
        }
    }
}