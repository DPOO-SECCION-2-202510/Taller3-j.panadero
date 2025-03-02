package uniandes.dpoo.aerolinea.persistencia;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.Avion;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {
    
    public PersistenciaAerolineaJson() {
        super();
    }

    @Override
    public void cargarAerolinea​(java.lang.String archivo,Aerolinea aerolinea) throws IOException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        cargarRutas(aerolinea, raiz.getJSONArray("rutas"));
        cargarVuelos(aerolinea, raiz.getJSONArray("vuelos"));
    }

    @Override
    public void salvarAerolinea​(java.lang.String archivo,Aerolinea aerolinea) throws IOException {
        JSONObject jobject = new JSONObject();
        salvarRutas(aerolinea, jobject);
        salvarVuelos(aerolinea, jobject);
        
        try (PrintWriter pw = new PrintWriter(archivo)) {
            jobject.write(pw, 2, 0);
        }
    }

    private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) {
        for (int i = 0; i < jRutas.length(); i++) {
            JSONObject rutaJson = jRutas.getJSONObject(i);
            String codigoRuta = rutaJson.getString("codigoRuta");
            String origenCodigo = rutaJson.getString("origen");
            String destinoCodigo = rutaJson.getString("destino");
            String horaLlegada = rutaJson.getString("horaLlegada");
            String horaSalida = rutaJson.getString("horaSalida");
            
            Aeropuerto origen = aerolinea.getAeropuerto(origenCodigo);
            Aeropuerto destino = aerolinea.getAeropuerto(destinoCodigo);
            Ruta ruta = new Ruta(horaSalida, horaLlegada,codigoRuta, origen,destino);
            
            aerolinea.getRutas().add(ruta);
        }
    }

    private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) {
        for (int i = 0; i < jVuelos.length(); i++) {
            JSONObject vueloJson = jVuelos.getJSONObject(i);
            String codigoRuta = vueloJson.getString("codigoRuta");
            String fecha = vueloJson.getString("fecha");
            String modeloAvion = vueloJson.getString("modeloAvion");
            
            Ruta ruta = aerolinea.getRuta(codigoRuta);
            Avion avion = aerolinea.getAvion(modeloAvion);
            
            if (ruta != null && avion != null) {
                Vuelo vuelo = new Vuelo(avion,fecha,ruta);
                aerolinea.getVuelos().add(vuelo);
            }
        }
    }

    private void salvarRutas(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jRuta = new JSONObject();
            jRuta.put("codigoRuta", ruta.getCodigoRuta());
            jRuta.put("origen", ruta.getOrigen().getCodigo());
            jRuta.put("destino", ruta.getDestino().getCodigo());
            jRuta.put("horaSalida", ruta.getHoraSalida());
            jRuta.put("horaLlegada", ruta.getHorLlegada());
            jRutas.put(jRuta);
        }
        jobject.put("rutas", jRutas);
    }

    private void salvarVuelos(Aerolinea aerolinea, JSONObject jobject) {
        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put("codigoRuta", vuelo.getRuta().getCodigoRuta());
            jVuelo.put("fecha", vuelo.getFecha());
            jVuelos.put(jVuelo);
        }
        jobject.put("vuelos", jVuelos);
    }
}
