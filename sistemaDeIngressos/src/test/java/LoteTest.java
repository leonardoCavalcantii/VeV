import org.example.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoteTest {

    private static final String TIPO_VIP = "VIP";
    private static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";
    private static final double PRECO_VIP = 20.0;
    private static final double PRECO_NORMAL = 10.0; // Preço de ingresso normal
    private static final double DESCONTO = 0.15;

    private Lote lote;

    @BeforeEach
    void setup() {
        lote = new Lote(1, DESCONTO);
    }

    @Test
    void calcularPrecoFinalComDescontoParaVip() {
        double precoFinal = lote.calcularPrecoFinal(TIPO_VIP, PRECO_VIP);
        assertEquals(17.0, precoFinal, 0.01); // 20 - 15%
    }

    @Test
    void naoDeveAplicarDescontoParaMeiaEntrada() {
        double precoFinal = lote.calcularPrecoFinal(TIPO_MEIA_ENTRADA, PRECO_NORMAL);
        assertEquals(5.0, precoFinal, 0.01); // 10 / 2 = 5 (sem desconto)
    }

    @Test
    void excecaoParaPrecoNegativo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lote.calcularPrecoFinal("NORMAL", -10.0)
        );
        assertEquals("Tipo de ingresso ou preço inválido", exception.getMessage());
    }

    @Test
    void excecaoParaTipoNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> lote.calcularPrecoFinal(null, 10.0)
        );
        assertEquals("Tipo de ingresso ou preço inválido", exception.getMessage());
    }
}
