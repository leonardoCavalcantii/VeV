import org.example.Pagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PagamentoTest {

    @Test
    public void test01() {
        Pagamento pagamento = new Pagamento(100.0, "2023-02-20", "BOLETO");

        assertEquals(100.0, pagamento.getValor(), 0.0001);
        assertEquals(LocalDate.of(2023, 2, 20), pagamento.getData());
        assertEquals("BOLETO", pagamento.getTipo());

    }
}
