package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    private java.lang.String horaSalida;
    private java.lang.String horLlegada;
    private java.lang.String codigoRuta;
    private Aeropuerto origen;
    private Aeropuerto destino;
    
    

    public Ruta(java.lang.String horaSalida, java.lang.String horLlegada, java.lang.String codigoRuta, Aeropuerto origen, Aeropuerto destino) {
		super();
		this.horaSalida = horaSalida;
		this.horLlegada = horLlegada;
		this.codigoRuta = codigoRuta;
		this.origen = origen;
		this.destino = destino;
	}
    

	public java.lang.String getHoraSalida() {
		return horaSalida;
	}



	public java.lang.String getHorLlegada() {
		return horLlegada;
	}





	public java.lang.String getCodigoRuta() {
		return codigoRuta;
	}





	public Aeropuerto getOrigen() {
		return origen;
	}





	public Aeropuerto getDestino() {
		return destino;
	}


	public int getDuracion() {
	    int horasSalida = getHoras(horaSalida);
	    int minutosSalida = getMinutos(horaSalida);
	    int horasLlegada = getHoras(horLlegada);
	    int minutosLlegada = getMinutos(horLlegada);

	    int minutosSalidaTotales = horasSalida * 60 + minutosSalida;
	    int minutosLlegadaTotales = horasLlegada * 60 + minutosLlegada;

	    int duracion = minutosLlegadaTotales - minutosSalidaTotales;
	    
	    if (duracion < 0) { 
	    	duracion += 24 * 60;
	    }

	    return duracion;
	}



	/**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    
}
