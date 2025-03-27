package co.edu.poli.ces3.gestoreventosdeportivos.models;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int id;
    private String nombre;
    private String deporte;
    private String ciudad;
    private String fechaFundacion;
    private String logo; // URL del logo
    private List<Integer> jugadores; // IDs de los jugadores

    public Equipo(int id, String nombre, String deporte, String ciudad, String fechaFundacion, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.deporte = deporte;
        this.ciudad = ciudad;
        this.fechaFundacion = fechaFundacion;
        this.logo = logo;
        this.jugadores = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDeporte() { return deporte; }
    public void setDeporte(String deporte) { this.deporte = deporte; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getFechaFundacion() { return fechaFundacion; }
    public void setFechaFundacion(String fechaFundacion) { this.fechaFundacion = fechaFundacion; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public List<Integer> getJugadores() { return jugadores; }
    public void agregarJugador(int jugadorId) { this.jugadores.add(jugadorId); }
}
