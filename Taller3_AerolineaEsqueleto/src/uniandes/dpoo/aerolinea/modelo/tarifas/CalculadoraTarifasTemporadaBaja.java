package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas{
	protected static final int COSTO_POR_KM_NATURAL = 600;
	protected static final int COSTO_POR_KM_CORPORATIVO = 900;
	protected static final double DESCUENTO_PEQ = 0.02;
	protected static final double DESCUENTO_MEDIANAS = 0.01;
	protected static final double DESCUENTO_GRANDES = 0.02;
	
    public int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        Ruta ruta = vuelo.getRuta();
        int distancia = calcularDistanciaVuelo(ruta);

        if (cliente instanceof ClienteCorporativo) {
        	return distancia * COSTO_POR_KM_CORPORATIVO; 
        } else {
        	return distancia * COSTO_POR_KM_NATURAL; 
        }

    }
    public double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente instanceof ClienteCorporativo) {
            int tamano = ((ClienteCorporativo) cliente).getTamanoEmpresa();
            if (tamano ==3) {
                
            	return DESCUENTO_PEQ;
            }else if(tamano==2){
            	
      
            	return DESCUENTO_MEDIANAS;
            }else {

                return DESCUENTO_GRANDES;
            }
        }
        return 0.0; 
    }

}
