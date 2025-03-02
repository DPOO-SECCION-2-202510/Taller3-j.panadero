package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public abstract class CalculadoraTarifas {
	public static final double IMPUESTO = 0.28;
	
	public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
		int costoBase = calcularCostoBase(vuelo, cliente); 
	    double porcentajeDescuento = calcularPorcentajeDescuento(cliente); 
	    double descuento = costoBase * (porcentajeDescuento / 100.0); 
	    double subtotal = costoBase - descuento; 

	    double impuesto = subtotal * IMPUESTO; 
	    int tarifaFinal = (int) Math.round(subtotal + impuesto); 

	    return tarifaFinal;
	}
	protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);
	protected abstract double calcularPorcentajeDescuento(Cliente cliente);
	protected int calcularDistanciaVuelo(Ruta ruta) {
		int distancia = Aeropuerto.calcularDistancia(ruta.getDestino(), ruta.getOrigen());
	    return distancia;
	}
	protected int calcularValorImpuestos(int costoBase) {
	    return (int) Math.round(costoBase * IMPUESTO);
	}



}
