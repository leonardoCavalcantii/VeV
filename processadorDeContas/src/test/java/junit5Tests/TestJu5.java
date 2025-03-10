package junit5Tests;

import org.example.Fatura;
import org.example.Pagamento;
import org.junit.jupiter.api.*;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestJu5 {

    private Fatura fatura;

    @BeforeEach
    void setUp() {
        fatura = new Fatura("Ana Silva", 1000.0, "2025-01-01");
    }

    @Test
    @DisplayName("Fatura criada corretamente")
    @Order(1)
    void testCriarFatura() {
        assertAll(
                () -> assertEquals("Ana Silva", fatura.getCliente()),
                () -> assertEquals(1000.0, fatura.getValor(), 0.0001),
                () -> assertEquals(LocalDate.of(2025, 1, 1), fatura.getData()),
                () -> assertEquals("PENDENTE", fatura.getEstado())
        );
    }

    @Test
    @DisplayName("Adiciona pagamento corretamente")
    @Order(2)
    void testeAdicionarPagamento() {
        fatura.adicionarPagamento(new Pagamento(500.0, "2024-12-31", "BOLETO"));
        fatura.adicionarPagamento(new Pagamento(600.0, "2024-12-30", "CARTAO"));

        assertAll(
                () -> assertEquals(2, fatura.getPagamentos().size()),
                () -> assertEquals(1100.0, fatura.getSomaPagamentos(), 0.0001)
        );
    }

    @Test
    @DisplayName("Fatura paga quando o valor total é atingido")
    void testeFaturaPaga() {
        fatura.adicionarPagamento(new Pagamento(500.0, "2024-12-31", "BOLETO"));
        fatura.adicionarPagamento(new Pagamento(500.0, "2024-12-31", "BOLETO"));
        assertEquals("PAGA", fatura.getEstado());
    }

    
    @Test
    @DisplayName("Não permite pagamento inválido")
    void testPagamentoInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                fatura.adicionarPagamento(new Pagamento(-100.0, "2024-12-31", "BOLETO")));
    }

    @Test
    @DisplayName("Boleto vencido gera juros de 10%")
    void boletoValorAtrasado() {
        fatura.adicionarPagamento(new Pagamento(50.0, "2025-01-02", "BOLETO"));
        assertEquals(55.0, fatura.getSomaPagamentos(), 0.0001);
    }

    @Test
    @DisplayName("Cartão fora do prazo não é aceito")
    void testaCartaoForaDaData() {
        fatura.adicionarPagamento(new Pagamento(50.0, "2024-12-16", "CARTAO"));
        assertEquals(0, fatura.getPagamentos().size());
    }

    @Test
    @DisplayName("Boleto acima de 5000 é inválido")
    void boletoAcima5kInvalido() {
        fatura.adicionarPagamento(new Pagamento(10000.0, "2024-12-31", "BOLETO"));
        assertEquals(0.0, fatura.getSomaPagamentos(), 0.0001);
        assertEquals("PENDENTE", fatura.getEstado());
    }

    @Test
    @DisplayName("Fatura paga exatamente com o valor devido")
    void testPagamentoExato() {
        fatura.adicionarPagamento(new Pagamento(1000.0, "2024-12-31", "BOLETO"));
        assertEquals("PAGA", fatura.getEstado());
    }
}
