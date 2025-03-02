package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class Tiquete {
	private java.lang.String codigo;
	private int tarifa;
	private boolean usado;
	private Vuelo vuelo;
	private Cliente cliente;
	public Tiquete(java.lang.String codigo, int tarifa, Vuelo vuelo, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.vuelo = vuelo;
		this.cliente = cliente;
		this.usado = false;
		
	}
	public String getCodigo() {
		return codigo;
	}

	public int getTarifa() {
		return tarifa;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void marcarComoUsado() {
		this.usado = true;
	}
	public boolean esUsado() {
		return usado;
	}
	
	

}
