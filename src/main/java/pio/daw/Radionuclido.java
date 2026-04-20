package pio.daw;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Radionuclido {

    // ── Tarea 1: declara aquí los atributos privados ──────────────────────────
    private String id;
    private String isotopo;
    private double actividadEspecificaInicial;
    private double masa;
    private LocalDateTime fechaEntrega;
    private LocalDateTime fechaSegura;


    // ── Tarea 1: constructor ──────────────────────────────────────────────────

    public Radionuclido(String id, String isotopo, Double actividadEspecificaInicial,
                        Double masa, LocalDateTime fechaEntrega, LocalDateTime fechaSegura ) {
        //TODO
        this.id = id;
        this.isotopo = isotopo;
        this.actividadEspecificaInicial = actividadEspecificaInicial;
        this.masa = masa;
        this.fechaEntrega = fechaEntrega;

    }

    // ── Tarea 1: getters ──────────────────────────────────────────────────────

    public String getId() {
        return null; // TODO
    }

    public String getIsotopo() {
        return id; // TODO
    }

    public Double getMasa() {
        return masa; // TODO
    }

    public Double getActividadEspecificaInicial() {
        return actividadEspecificaInicial; // TODO
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega; // TODO
    }

    public double getActividadInicial() {
        return actividadEspecificaInicial * masa; // TODO
    }

    // ── Tarea 2: actividad en una fecha concreta ──────────────────────────────

    public double actividad(LocalDateTime fecha) {
        double semivida = Utilidades.semividas.get(this.isotopo);
             return this.getActividadInicial() * Math.exp(Math.log(2) / semivida 
             * ChronoUnit.SECONDS.between(this.fechaEntrega, fecha));
    }

    // ── Tarea 3: fracción de actividad restante (0..1) ────────────────────────

    public double porcentajeActividad(LocalDateTime fecha) {
        return actividad(fecha) / getActividadInicial(); // TODO
    }

    // ── Tarea 4: fecha en que la actividad baja al 10% ───────────────────────

    public LocalDateTime getFechaSegura() {
        double semivida = Utilidades.semividas.get(isotopo);

        double tMax = semivida;
        while (porcentajeActividad(fechaEntrega.plusSeconds((long) tMax) ) >= 0.1) {
            tMax *= 2;
        }

        double tSeg = Utilidades.biseccion(
           t -> porcentajeActividad(fechaEntrega.plusSeconds((long) t)),
                0.1,
                 0,
                tMax

           );
        return this.fechaEntrega.plusSeconds((long) tSeg); // TODO
    }

    // ── Tarea 5: coste de refrigeración en euros ──────────────────────────────

    public double getCosteRefrigeracion() {
            LocalDateTime fechaSegura = getFechaSegura();
            double tSeg = ChronoUnit.SECONDS.between(fechaSegura, fechaSegura);
            double eDesintegracion = Utilidades.energias.get(isotopo);

            FuncionUnivariable pEle = t ->
            actividad(fechaEntrega.plusSeconds((long) t)) * eDesintegracion / 4.0;

            double eGastada = Utilidades.integrar(pEle, 0, tSeg);

            return eGastada / 3_600_000.0;
    }

    // ── Tarea 6: bloque de factura en texto ───────────────────────────────────


    public String toFactura() {

        return String.format("====================\n" 
        + "FACTURA DE REFRIGERACIÓN - RESIDUO #1 \n" 
        +  "====================\n" + "Isótopo : %s \n" + isotopo +"Masa : %.f kg \n" + masa);
        
         DateTimeFormatter fechaEntrega = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm \n");
         String texto1String = fechaEntrega.format(getFechaEntrega());

         DateTimeFormatter fechaSegura = DateTimeFormatter.ofPattern("yyyy-MM-dd 'T'HH:mm \n");
         String texto2String = fechaSegura.format(getFechaSegura());
      
        return String.format( "Coste total : %.2f € \n" + getCosteRefrigeracion() + "-------------------"); // TODO
         }


}
