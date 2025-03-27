package co.edu.poli.ces3.gestoreventosdeportivos.dao;

import co.edu.poli.ces3.gestoreventosdeportivos.models.Jugador;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JugadorDAO {
    private static List<Jugador> jugadores = new ArrayList<>();
    private static int contadorId = 1;

    public List<Jugador> obtenerTodos() {
        return new ArrayList<>(jugadores); // Devuelve una copia de la lista de jugadores
    }
    public boolean numeroEnUso(int numero, int equipoId) {
        for (Jugador jugador : jugadores) {
            if (jugador.getEquipoId() == equipoId && jugador.getNumero() == numero) {
                return true; // Si hay un jugador con el mismo número en el equipo, retorna true
            }
        }
        return false; // Si no se encuentra, retorna false
    }

    public void agregar(Jugador jugador) {
        jugadores.add(jugador); // Agrega el nuevo jugador a la lista
    }

    // Método para agregar un jugador
    public boolean agregarJugador(Jugador jugador) {
        // Verificar que el número de camiseta no esté repetido en el mismo equipo
        boolean existe = jugadores.stream()
                .anyMatch(j -> j.getEquipoId() == jugador.getEquipoId() && j.getNumero() == jugador.getNumero());

        if (existe) {
            return false; // Número de camiseta ya utilizado en ese equipo
        }

        jugador.setId(contadorId++);
        jugadores.add(jugador);
        return true;
    }

    // Método para obtener todos los jugadores
    public List<Jugador> obtenerJugadores() {
        return jugadores;
    }

    // Método para obtener un jugador por ID
    public Optional<Jugador> obtenerJugadorPorId(int id) {
        return jugadores.stream().filter(j -> j.getId() == id).findFirst();
    }

    // Método para transferir un jugador de equipo
    public boolean transferirJugador(int jugadorId, int equipoDestino) {
        Optional<Jugador> jugadorOpt = obtenerJugadorPorId(jugadorId);

        if (jugadorOpt.isPresent()) {
            Jugador jugador = jugadorOpt.get();
            jugador.setEquipoId(equipoDestino);
            return true;
        }
        return false;
    }
}
