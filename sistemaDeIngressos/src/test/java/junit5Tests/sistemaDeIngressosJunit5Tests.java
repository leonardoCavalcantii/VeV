package junit5Tests;

import org.example.Ingresso;
import org.example.Lote;
import org.example.Show;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestIngressoLoteShow {

    private Ingresso ingresso;
    private Lote lote;
    private Show show;

    private static final String TIPO_NORMAL = "NORMAL";
    private static final String TIPO_VIP = "VIP";
    private static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";
    private static final String STATUS_DISPONIVEL = "DISPONIVEL";
    private static final String STATUS_VENDIDO = "VENDIDO";
    private static final double PRECO_VIP = 20.0;
    private static final double PRECO_NORMAL = 10.0;
    private static final double DESCONTO = 0.15;

    @BeforeEach
    void setUp() {
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
        lote = new Lote(1, DESCONTO);
    }

    @Test
    @DisplayName("Deve marcar ingresso como vendido")
    void deveMarcarIngressoComoVendido() {
        ingresso.marcarComoVendido();
        assertEquals(STATUS_VENDIDO, ingresso.getStatus());
    }

    @Test
    @DisplayName("Não deve marcar ingresso já vendido como vendido")
    void naoDeveMarcarIngressoJaVendidoComoVendido() {
        ingresso.marcarComoVendido();
        RuntimeException exception = assertThrows(RuntimeException.class, ingresso::marcarComoVendido);
        assertEquals("já está como vendido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve criar ingresso com tipo correto")
    void deveCriarIngressoComTipo() {
        assertAll(
                () -> assertEquals(TIPO_NORMAL, new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL).getTipo()),
                () -> assertEquals(TIPO_VIP, new Ingresso(1, TIPO_VIP, STATUS_DISPONIVEL).getTipo()),
                () -> assertEquals(TIPO_MEIA_ENTRADA, new Ingresso(1, TIPO_MEIA_ENTRADA, STATUS_DISPONIVEL).getTipo())
        );
    }

    @Test
    @DisplayName("Deve validar status ao criar ingresso")
    void deveValidarStatusAoCriarIngresso() {
        assertEquals(STATUS_DISPONIVEL, ingresso.getStatus());
    }

    @Test
    @DisplayName("Não deve criar ingresso com status inválido")
    void naoDeveCriarIngressoComStatusInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Ingresso(1, TIPO_NORMAL, "INVALIDO"));
        assertEquals("Status de ingresso inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve calcular preço final com desconto para VIP")
    void calcularPrecoFinalComDescontoParaVip() {
        assertEquals(17.0, lote.calcularPrecoFinal(TIPO_VIP, PRECO_VIP), 0.01);
    }

    @Test
    @DisplayName("Não deve aplicar desconto para meia entrada")
    void naoDeveAplicarDescontoParaMeiaEntrada() {
        assertEquals(5.0, lote.calcularPrecoFinal(TIPO_MEIA_ENTRADA, PRECO_NORMAL), 0.01);
    }

    @Test
    @DisplayName("Exceção para preço negativo")
    void excecaoParaPrecoNegativo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                lote.calcularPrecoFinal("NORMAL", -10.0));
        assertEquals("Tipo de ingresso ou preço inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Exceção para tipo nulo")
    void excecaoParaTipoNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                lote.calcularPrecoFinal(null, 10.0));
        assertEquals("Tipo de ingresso ou preço inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve calcular receita líquida com despesas e cachê")
    void calcularReceitaLiquidaComDespesasECache() {
        show = new Show(1000.0, 2000.0, false);
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);
        assertEquals(14500, show.calcularReceitaLiquida(), 0.01);
    }

    @Test
    @DisplayName("Verificar status financeiro do show")
    void verificarStatusFinanceiro() {
        show = new Show(3000.0, 2000.0, false);
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);
        assertEquals("LUCRO", show.statusFinanceiro());

        show = new Show(8750.0, 8750.0, false);
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);
        assertEquals("ESTÁVEL", show.statusFinanceiro());

        show = new Show(10000.0, 5000.0, false);
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);
        assertEquals("LUCRO", show.statusFinanceiro());
    }
}
