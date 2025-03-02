package uniandes.dpoo.aerolinea.modelo;

import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
	private Avion avion;
	private java.lang.String fecha;	
	private Ruta ruta;	 
	private java.util.Map<java.lang.String, Tiquete> tiquetes;

	
	public Vuelo(Avion avion, java.lang.String fecha, Ruta ruta) {
		super();
		this.avion = avion;
		this.fecha = fecha;
		this.ruta = ruta;
	    this.tiquetes = new HashMap<>();

	}

	public Avion getAvion() {
		return avion;
	}


	public java.lang.String getFecha() {
		return fecha;
	}



	public Ruta getRuta() {
		return ruta;
	}


	public java.util.Map<java.lang.String, Tiquete> getTiquetes() {
		return tiquetes;
	}
	
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Vuelo otroVuelo = (Vuelo) obj;
	    return this.fecha.equals(otroVuelo.fecha) &&
	           this.ruta.equals(otroVuelo.ruta) &&
	           this.avion.equals(otroVuelo.avion);
	}
	public int venderTiquetes​(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) 
			throws VueloSobrevendidoException{
		int capacidadDisponible = avion.getCapacidad() - tiquetes.size();
	    
	    if (cantidad > capacidadDisponible) {
	        throw new VueloSobrevendidoException(this);
	    }

	    int tarifa = calculadora.calcularTarifa(this,cliente); // Se calcula la tarifa del vuelo
	    int totalVenta = tarifa * cantidad;

	    for (int i = 0; i < cantidad; i++) {
	        Tiquete tiquete = GeneradorTiquetes.generarTiquete(this, cliente, tarifa);
	        tiquetes.put(tiquete.getCodigo(), tiquete);
	        cliente.agregarTiquete(tiquete);
	    }

	    return totalVenta; 
		
	}

	

}
