package uniandes.dpoo.aerolinea.persistencia;
import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;




public interface IPersistenciaAerolinea {
	public void cargarAerolinea​(java.lang.String archivo,Aerolinea aerolinea)throws java.io.IOException, InformacionInconsistenteException;
	
	public void salvarAerolinea​(java.lang.String archivo,Aerolinea aerolinea)throws java.io.IOException;
}

