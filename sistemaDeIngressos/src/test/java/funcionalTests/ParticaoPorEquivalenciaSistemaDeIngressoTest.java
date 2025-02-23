import org.example.Ingresso;
import org.example.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParticaoPorEquivalenciaSistemaDeIngressoTest {

    private static final String TIPO_NORMAL = "NORMAL";
    private static final String TIPO_VIP = "VIP";
    private static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";

    private static final String STATUS_DISPONIVEL = "DISPONIVEL";
    private static final String STATUS_VENDIDO = "VENDIDO";

    private Ingresso ingresso;
    private Lote lote;

    @BeforeEach
    void setUp() {
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
        lote = new Lote(1, 0.1);
    }

    @Test
    void deveCalcularPrecoIngressoVipComDesconto() {
        assertEquals(18.0, lote.calcularPrecoFinal(TIPO_VIP, 20.0), 0.01);
    }

    @Test
    void deveCalcularPrecoIngressoMeiaEntrada() {
        assertEquals(10.0, lote.calcularPrecoFinal(TIPO_MEIA_ENTRADA, 20.0), 0.01);
    }

    @Test
    void deveCalcularPrecoIngressoNormalComDesconto() {
        assertEquals(18.0, lote.calcularPrecoFinal(TIPO_NORMAL, 20.0), 0.01);
    }

    @Test
    void deveLancarExcecaoParaPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                        lote.calcularPrecoFinal(TIPO_NORMAL, -10.0),
                "Preço negativo deveria lançar exceção."
        );
    }

    @Test
    void deveLancarExcecaoParaTipoIngressoInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                        lote.calcularPrecoFinal("INVALIDO", 20.0),
                "Tipo de ingresso inválido deveria lançar exceção."
        );
    }

    @Test
    void deveCalcularPrecoComPrecoZero() {
        assertEquals(0.0, lote.calcularPrecoFinal(TIPO_NORMAL, 0.0), 0.01);
    }
}
