package pio.daw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UtilidadesTest {

    private static final double TOLERANCIA = 1e-4;

    // ── energias ──────────────────────────────────────────────────────────────

    @Test
    void energias_contieneLosIsotopos() {
        assertNotNull(Utilidades.energias.get("U235"));
        assertNotNull(Utilidades.energias.get("Pu239"));
        assertNotNull(Utilidades.energias.get("Cs137"));
    }

    @Test
    void energias_valoresPositivos() {
        assertTrue(Utilidades.energias.get("U235") > 0);
        assertTrue(Utilidades.energias.get("Pu239") > 0);
        assertTrue(Utilidades.energias.get("Cs137") > 0);
    }

    // ── semividas ─────────────────────────────────────────────────────────────

    @Test
    void semividas_contieneLosIsotopos() {
        assertNotNull(Utilidades.semividas.get("U235"));
        assertNotNull(Utilidades.semividas.get("Pu239"));
        assertNotNull(Utilidades.semividas.get("Cs137"));
    }

    @Test
    void semividas_ordenCorrecto() {
        // U235 (muy largo) > Pu239 > Cs137 (más corto)
        double u235  = Utilidades.semividas.get("U235");
        double pu239 = Utilidades.semividas.get("Pu239");
        double cs137 = Utilidades.semividas.get("Cs137");

        assertTrue(u235 > pu239);
        assertTrue(pu239 > cs137);
    }

    // ── integrar ──────────────────────────────────────────────────────────────

    @Test
    void integrar_funcionConstante() {
        // ∫₀¹⁰ 3 dt = 30
        double resultado = Utilidades.integrar(t -> 3.0, 0, 10);
        assertEquals(30.0, resultado, TOLERANCIA);
    }

    @Test
    void integrar_funcionLineal() {
        // ∫₀⁴ t dt = 8
        double resultado = Utilidades.integrar(t -> t, 0, 4);
        assertEquals(8.0, resultado, TOLERANCIA);
    }

    @Test
    void integrar_funcionCuadratica() {
        // ∫₀³ t² dt = 9
        double resultado = Utilidades.integrar(t -> t * t, 0, 3);
        assertEquals(9.0, resultado, TOLERANCIA);
    }

    @Test
    void integrar_seno() {
        // ∫₀^π sin(t) dt = 2
        double resultado = Utilidades.integrar(Math::sin, 0, Math.PI);
        assertEquals(2.0, resultado, TOLERANCIA);
    }

    // ── byeccion ──────────────────────────────────────────────────────────────

    @Test
    void byeccion_funcionCreciente() {
        // f(x) = x; f(x) = 5 → x = 5
        double resultado = Utilidades.byeccion(t -> t, 5.0, 0, 10);
        assertEquals(5.0, resultado, TOLERANCIA);
    }

    @Test
    void byeccion_funcionDecreciente() {
        // f(x) = 10 - x; f(x) = 3 → x = 7
        double resultado = Utilidades.byeccion(t -> 10 - t, 3.0, 0, 10);
        assertEquals(7.0, resultado, TOLERANCIA);
    }

    @Test
    void byeccion_exponencialDecreciente() {
        // f(x) = e^(-x); f(x) = 0.5 → x = ln(2) ≈ 0.6931
        double resultado = Utilidades.byeccion(t -> Math.exp(-t), 0.5, 0, 10);
        assertEquals(Math.log(2), resultado, TOLERANCIA);
    }
}
