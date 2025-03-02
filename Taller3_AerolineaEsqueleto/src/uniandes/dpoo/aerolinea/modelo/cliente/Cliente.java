package uniandes.dpoo.aerolinea.modelo.cliente;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    public List<Tiquete> tiquetesSinUsar;
    private List<Tiquete> tiquetesUsados;
    
    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados = new ArrayList<>();
    }
    
    public List<Tiquete> getTiquetesSinUsar() {
		return tiquetesSinUsar;
	}



	public List<Tiquete> getTiquetesUsados() {
		return tiquetesUsados;
	}



	public void agregarTiquete(Tiquete tiquete) {
        tiquetesSinUsar.add(tiquete);
    }
    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete tiquete : tiquetesSinUsar) {
            total += tiquete.getTarifa();
        }
        for (Tiquete tiquete : tiquetesUsados) {
            total += tiquete.getTarifa();
        }
        return total;
    }
    public void usarTiquetes(Vuelo vuelo) {
        List<Tiquete> tiquetesParaMover = new ArrayList<>();
        for (Tiquete tiquete : tiquetesSinUsar) {
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquetesParaMover.add(tiquete);
            }
        }
        tiquetesSinUsar.removeAll(tiquetesParaMover);
        tiquetesUsados.addAll(tiquetesParaMover);
    }

    public abstract String getIdentificador();

    public abstract String getTipoCliente();

}
