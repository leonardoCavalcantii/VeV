import org.example.Fatura;
import org.example.Pagamento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FaturaTest {

    //toda fatura por defalt eh iniciada como PENDENTE.
    //adicao de novos testes para checar funcionamento correto da criacao da fatura!
    @Test
    public void testCriarFtura() {
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17");
        assertEquals("Joao Silva", fatura.getCliente());
        assertEquals(1500.0, fatura.getValor(), 0.0001);
        assertEquals(LocalDate.of(2020, 2, 17), fatura.getData());
        assertEquals("PENDENTE", fatura.getEstado());

    }

    @Test
    public void testeAdicionarPagamento() {
        Fatura fatura = new Fatura("Joao Silva", 1100.0, "2025-01-01");
        Pagamento pagamento1 = new Pagamento(500.0, "2024-12-31", "Boleto");
        Pagamento pagamento2 = new Pagamento(600.0, "2024-12-30", "CARTAO");

        fatura.adicionarPagamento(pagamento1);
        fatura.adicionarPagamento(pagamento2);

        assertEquals(2, fatura.getPagamentos().size());
        assertEquals(1100.0, fatura.getSomaPagamentos(), 0.0001);
    }
}
