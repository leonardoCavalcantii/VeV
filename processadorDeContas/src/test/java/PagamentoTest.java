import org.example.Fatura;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PagamentoTest {

    @Test
    public void test01() {
        Pagamento pagamento = new Pagamento(100.0, "2023-02-20", "BOLETO");

        assertEquals(100.0, pagamento.getValor());
        assertEquals("2023-02-20", pagamento.getData());
        assertEquals("BOLETO", pagamento.getTipo());

    }
}
