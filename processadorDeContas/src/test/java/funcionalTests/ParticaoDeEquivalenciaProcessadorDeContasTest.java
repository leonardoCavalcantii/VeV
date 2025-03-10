package funcionalTests;

import org.example.Fatura;
import org.example.Pagamento;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParticaoDeEquivalenciaProcessadorDeContasTest {


    @Test
    @DisplayName("fatura criada")
    @Order(2)
    public void testCriarFtura() {
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17");
        assertEquals("Joao Silva", fatura.getCliente());
        assertEquals(1500.0, fatura.getValor(), 0.0001);
        assertEquals(LocalDate.of(2020, 2, 17), fatura.getData());
        assertEquals("PENDENTE", fatura.getEstado());

    }

    @Test
    @DisplayName("Adiciona pagamento")
    @Order(1)
    public void testeAdicionarPagamento() {
        Fatura fatura = new Fatura("Joao Silva", 1100.0, "2025-01-01");
        Pagamento pagamento1 = new Pagamento(500.0, "2024-12-31", "BOLETO");
        Pagamento pagamento2 = new Pagamento(600.0, "2024-12-30", "CARTAO");

        fatura.adicionarPagamento(pagamento1);
        fatura.adicionarPagamento(pagamento2);

        assertEquals(2, fatura.getPagamentos().size());
        assertEquals(1100.0, fatura.getSomaPagamentos(), 0.0001);
    }

    @Test
    public void testeFaturaPaga() {
        Fatura fatura = new Fatura("Ana Silva", 1000.0, "2025-01-01");
        Pagamento pagamento1 = new Pagamento(500.0, "2024-12-31", "BOLETO");
        Pagamento pagamento2 = new Pagamento(500.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento1);
        fatura.adicionarPagamento(pagamento2);

        assertEquals("PAGA", fatura.getEstado());
    }

    @Test
    public void testeFaturaNaoPaga() {
        Fatura fatura = new Fatura("Ana Silva", 1000.0, "2025-01-01");
        Pagamento pagamento1 = new Pagamento(5.0, "2024-12-31", "BOLETO");
        Pagamento pagamento2 = new Pagamento(500.0, "2024-12-31", "BOLETO");

        fatura.adicionarPagamento(pagamento1);
        fatura.adicionarPagamento(pagamento2);

        assertEquals("PENDENTE", fatura.getEstado());
    }

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

