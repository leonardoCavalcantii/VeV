
import org.example.Lote;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class LoteTest {

    private Lote lote;

    @Test
    void calcularPrecoFinalComDescontoParaVip() {
        lote = new Lote(1, 0.15);
        double precoFinal = lote.calcularPrecoFinal("VIP", 20.0);
        assertEquals(17.0, precoFinal, 0.01);
    }
}
