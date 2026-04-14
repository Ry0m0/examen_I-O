package pio.daw;

import java.time.LocalDateTime;

public class Radionuclido {

    // ── Tarea 1: declara aquí los atributos privados ──────────────────────────


    // ── Tarea 1: constructor ──────────────────────────────────────────────────

    public Radionuclido(String id, String isotopo, Double actividadEspecificaInicial,
                        Double masa, LocalDateTime fechaEntrega) {
        // TODO
    }

    // ── Tarea 1: getters ──────────────────────────────────────────────────────

    public String getId() {
        return null; // TODO
    }

    public String getIsotopo() {
        return null; // TODO
    }

    public Double getMasa() {
        return null; // TODO
    }

    public Double getActividadEspecificaInicial() {
        return null; // TODO
    }

    public LocalDateTime getFechaEntrega() {
        return null; // TODO
    }

    public double getActividadInicial() {
        return 0; // TODO
    }

    // ── Tarea 2: actividad en una fecha concreta ──────────────────────────────

    public double actividad(LocalDateTime fecha) {
        return 0; // TODO
    }

    // ── Tarea 3: fracción de actividad restante (0..1) ────────────────────────

    public double porcentajeActividad(LocalDateTime fecha) {
        return 0; // TODO
    }

    // ── Tarea 4: fecha en que la actividad baja al 10% ───────────────────────

    public LocalDateTime getFechaSegura() {
        return null; // TODO
    }

    // ── Tarea 5: coste de refrigeración en euros ──────────────────────────────

    public double getCosteRefrigeracion() {
        return 0; // TODO
    }

    // ── Tarea 6: bloque de factura en texto ───────────────────────────────────

    public String toFactura() {
        return null; // TODO
    }
}
