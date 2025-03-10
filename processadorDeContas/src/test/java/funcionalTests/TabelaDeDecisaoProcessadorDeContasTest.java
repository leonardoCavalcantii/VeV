package funcionalTests;

import org.example.Fatura;
import org.example.Pagamento;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaDeDecisaoProcessadorDeContasTest {
    @Test
    public void boletoValor5kInvalido() {
        Fatura fatura = new Fatura("Ana Silva", 10000.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(10000.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PENDENTE", fatura.getEstado());
    }

    @Test
    public void boletoValor0Invalido() {
        Fatura fatura = new Fatura("Ana Silva", 10000.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(0.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PENDENTE", fatura.getEstado());
    }

    @Test
    public void boletoValorAtrasadoInvalido() {
        Fatura fatura = new Fatura("Ana Silva", 50.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(50.0, "2025-01-02", "BOLETO");

        fatura.adicionarPagamento(pagamento);

        assertEquals(55.0, fatura.getSomaPagamentos(), 0.0001);
    }

    @Test
    public void testaCartaoDataCorreta() {
        Fatura fatura = new Fatura("Ana Silva", 50.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(50.0, "2024-12-17", "CARTAO");

        fatura.adicionarPagamento(pagamento);

        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    public void testaCartaoForaDaDataCorreta() {
        Fatura fatura = new Fatura("Ana Silva", 50.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(50.0, "2024-12-16", "CARTAO");

        fatura.adicionarPagamento(pagamento);

        assertEquals(0, fatura.getPagamentos().size());
    }
}
