package funcionalTests;

import org.example.Fatura;
import org.example.Pagamento;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ValoresLimiteProcessadorDeContasTest {
    //testando valores limites de uma operação de pagar fatura.
    @Test
    public void testPagamentoFturaValoresLimites1() {
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17");
        Pagamento pagamento1 = new Pagamento(1500.0, "2024-12-31", "BOLETO");
        fatura.adicionarPagamento(pagamento1);

        assertEquals("PAGA", fatura.getEstado());
        //testando se o valor da conta for igual ao valor da fatura.

    }

    @Test
    public void testPagamentoFturaValoresLimites2() {
        Fatura fatura = new Fatura("Joao Silva", 15000.0, "2020-02-17");
        Pagamento pagamento1 = new Pagamento(1500.0, "2024-12-31", "BOLETO");
        fatura.adicionarPagamento(pagamento1);

        assertEquals("PENDENTE", fatura.getEstado());
        //testando se o valor da conta for menor ao valor da fatura.

    }

    @Test
    public void testPagamentoFturaValoresLimites3() {
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17");
        Pagamento pagamento1 = new Pagamento(1501.0, "2024-12-31", "BOLETO");
        fatura.adicionarPagamento(pagamento1);

        assertEquals("PAGA", fatura.getEstado());
        //testando se o valor da conta for maior ao valor da fatura.

    }

    // testando valores limites do boleto.
    @Test
    public void boletoValorBoleto1() {
        Fatura fatura = new Fatura("Ana Silva", 5001.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(5001.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        //assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PENDENTE", fatura.getEstado());
        //valor acima do limite do boleto.
    }

    @Test
    public void boletoValorBoleto2() {
        Fatura fatura = new Fatura("Ana Silva", 5000.0, "2025-01-01");
        Pagamento pagamento = new Pagamento(5000.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        //assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PAGA", fatura.getEstado());

    }


    @Test
    public void boletoValorBoleto3() {
        Fatura fatura = new Fatura("Ana Silva", 0.005, "2025-01-01");
        Pagamento pagamento = new Pagamento(0.01, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        //assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PENDENTE", fatura.getEstado());
    }


    @Test
    public void boletoValorBoleto4() {
        Fatura fatura = new Fatura("Ana Silva", 0.01, "2025-01-01");
        Pagamento pagamento = new Pagamento(0.01, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        //assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PAGA", fatura.getEstado());
    }

    @Test
    public void boletoValorBoleto5() {
        Fatura fatura = new Fatura("Ana Silva", 100.01, "2025-01-01");
        Pagamento pagamento = new Pagamento(100.01, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento);
        //assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);

        assertEquals("PAGA", fatura.getEstado());
    }


}
