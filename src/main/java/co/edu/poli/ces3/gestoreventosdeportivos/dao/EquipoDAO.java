package co.edu.poli.ces3.gestoreventosdeportivos.dao;

import co.edu.poli.ces3.gestoreventosdeportivos.models.Equipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipoDAO {
    private static List<Equipo> equipos = new ArrayList<>();
    private static int contadorId = 1;

    public Equipo obtenerPorId(int id) {
        for (Equipo equipo : equipos) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }
    // Método para agregar un equipo
    public boolean agregarEquipo(Equipo equipo) {
        // Verificar si ya existe un equipo con el mismo nombre y deporte
        boolean existe = equipos.stream()
                .anyMatch(e -> e.getNombre().equalsIgnoreCase(equipo.getNombre())
                        && e.getDeporte().equalsIgnoreCase(equipo.getDeporte()));

        if (existe) {
            return false; // No se permite agregar equipos duplicados
        }

        equipo.setId(contadorId++);
        equipos.add(equipo);
        return true;
    }

    // Método para obtener todos los equipos con paginación
    public List<Equipo> obtenerEquipos(int page, int size) {
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, equipos.size());

        if (fromIndex >= equipos.size()) {
            return new ArrayList<>();
        }

        return equipos.subList(fromIndex, toIndex);
    }

    // Método para obtener un equipo por ID
    public Optional<Equipo> obtenerEquipoPorId(int id) {
        return equipos.stream().filter(e -> e.getId() == id).findFirst();
    }

    // Método para agregar un jugador a un equipo
    public boolean agregarJugadorAEquipo(int equipoId, int jugadorId) {
        Optional<Equipo> equipoOpt = obtenerEquipoPorId(equipoId);
        if (equipoOpt.isPresent()) {
            equipoOpt.get().agregarJugador(jugadorId);
            return true;
        }
        return false;
    }

    // Método para obtener todos los equipos sin paginación (para estadísticas)
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipos;
    }
}
