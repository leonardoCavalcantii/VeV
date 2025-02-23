import org.example.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValoresLimiteSistemaDeIngressoTest {

    private static final String TIPO_NORMAL = "NORMAL";
    private static final double DESCONTO_MAXIMO = 0.25;
    private static final double DESCONTO_MINIMO = 0.0;
    private static final double PRECO_NORMAL = 10.0;
    private static final double PRECISAO = 0.0001;

    private Lote lote;

    @BeforeEach
    void setup() {
        lote = new Lote(1, DESCONTO_MAXIMO);
    }

    @Test
    void deveAplicarDescontoMaximoDe25PorCento() {
        assertEquals(7.5, lote.calcularPrecoFinal(TIPO_NORMAL, PRECO_NORMAL), PRECISAO,
                "Preço final deveria ser 7.5 com desconto máximo de 25%.");
    }

    @Test
    void deveAplicarDescontoMinimoDeZeroPorCento() {
        lote = new Lote(1, DESCONTO_MINIMO);
        assertEquals(PRECO_NORMAL, lote.calcularPrecoFinal(TIPO_NORMAL, PRECO_NORMAL), PRECISAO,
                "Preço final deveria ser igual ao preço normal sem desconto.");
    }

    @Test
    void deveLancarExcecaoParaDescontoMaiorQuePermitido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Lote(1, 0.3),
                "Desconto não pode ser maior que 25%."
        );
    }

    @Test
    void deveAceitarIngressoComPrecoZero() {
        assertEquals(0.0, lote.calcularPrecoFinal(TIPO_NORMAL, 0.0), PRECISAO,
                "Preço final deveria ser 0.0 para ingresso gratuito.");
    }
}
