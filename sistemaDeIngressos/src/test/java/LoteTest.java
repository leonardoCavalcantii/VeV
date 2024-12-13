import org.example.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoteTest {

    private static final String TIPO_VIP = "VIP";
    private static final double PRECO_VIP = 20.0;
    private static final double DESCONTO = 0.15;

    private Lote lote;

    @BeforeEach
    void setup() {
        lote = new Lote(1, DESCONTO);
    }

    @Test
    void calcularPrecoFinalComDescontoParaVip() {
        double precoFinal = lote.calcularPrecoFinal(TIPO_VIP, PRECO_VIP);
        assertEquals(17.0, precoFinal, 0.01);
    }

    @Test
    void naoDeveAplicarDescontoParaMeiaEntrada() {
        Lote lote = new Lote(1, 0.15);
        double precoFinal = lote.calcularPrecoFinal("MEIA_ENTRADA", 5.0);
        assertEquals(5.0, precoFinal, 0.01);
    }
}
