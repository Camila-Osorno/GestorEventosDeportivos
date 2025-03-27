package co.edu.poli.ces3.gestoreventosdeportivos.dao;


import co.edu.poli.ces3.gestoreventosdeportivos.models.Evento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventoDAO {
    private static List<Evento> eventos = new ArrayList<>();
    private static int contadorId = 1;

    public List<Evento> obtenerTodos() {
        return eventos;
    }

    public void agregar(Evento evento) {
        eventos.add(evento);
    }

    // Método para agregar un evento
    public boolean agregarEvento(Evento evento) {
        // Verificar que el evento tenga al menos dos equipos participantes
        if (evento.getEquiposParticipantes().size() < 2) {
            return false; // No se puede registrar un evento con menos de dos equipos
        }
        evento.setId(contadorId++);
        eventos.add(evento);
        return true;
    }

    // Método para obtener todos los eventos
    public List<Evento> obtenerEventos() {
        return eventos;
    }

    // Método para obtener un evento por ID
    public Optional<Evento> obtenerEventoPorId(int id) {
        return eventos.stream().filter(e -> e.getId() == id).findFirst();
    }

    // Método para filtrar eventos por deporte, estado y/o rango de fechas
    public List<Evento> filtrarEventos(String deporte, String estado, String fechaInicio, String fechaFin) {
        return eventos.stream()
                .filter(e -> (deporte == null || e.getDeporte().equalsIgnoreCase(deporte)))
                .filter(e -> (estado == null || e.getEstado().equalsIgnoreCase(estado)))
                .filter(e -> (fechaInicio == null || e.getFecha().compareTo(fechaInicio) >= 0))
                .filter(e -> (fechaFin == null || e.getFecha().compareTo(fechaFin) <= 0))
                .collect(Collectors.toList());
    }

    // Método para vender entradas y actualizar la capacidad del evento
    public boolean venderEntradas(int eventoId, int cantidad) {
        Optional<Evento> eventoOpt = obtenerEventoPorId(eventoId);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            if (evento.getEntradasVendidas() + cantidad <= evento.getCapacidad()) {
                evento.setEntradasVendidas(evento.getEntradasVendidas() + cantidad);
                return true;
            }
        }
        return false;
    }
}
