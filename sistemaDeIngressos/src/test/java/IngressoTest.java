import org.example.Ingresso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngressoTest {

    private static final String TIPO_NORMAL = "NORMAL";
    private static final String TIPO_VIP = "VIP";
    private static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";

    private static final String STATUS_DISPONIVEL = "DISPONIVEL";
    private static final String STATUS_VENDIDO = "VENDIDO";

    private Ingresso ingresso;

    @BeforeEach
    void setUp() {
        // Criação do ingresso padrão antes de cada teste
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
    }

    @Test
    void deveMarcarIngressoComoVendido() {
        ingresso.marcarComoVendido();
        assertEquals(STATUS_VENDIDO, ingresso.getStatus());
    }

    @Test
    void naoDeveMarcarIngressoJaVendidoComoVendido() {
        ingresso.marcarComoVendido(); // Marca como vendido primeiro

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ingresso.marcarComoVendido();
        });

        assertEquals("já está como vendido", exception.getMessage());
    }

    @Test
    void deveCriarIngressoComTipoNormal() {
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
        assertEquals(TIPO_NORMAL, ingresso.getTipo());
    }

    @Test
    void deveCriarIngressoComTipoVip() {
        ingresso = new Ingresso(1, TIPO_VIP, STATUS_DISPONIVEL);
        assertEquals(TIPO_VIP, ingresso.getTipo());
    }

    @Test
    void deveCriarIngressoComTipoMeiaEntrada() {
        ingresso = new Ingresso(1, TIPO_MEIA_ENTRADA, STATUS_DISPONIVEL);
        assertEquals(TIPO_MEIA_ENTRADA, ingresso.getTipo());
    }

    @Test
    void deveValidarStatusAoCriarIngresso() {
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
        assertEquals(STATUS_DISPONIVEL, ingresso.getStatus());
    }

    @Test
    void naoDeveCriarIngressoComStatusInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ingresso(1, TIPO_NORMAL, "INVALIDO"); // Status inválido
        });

        assertEquals("Status de ingresso inválido", exception.getMessage());
    }

    @Test
    void deveCriarIngressoComIdValido() {
        ingresso = new Ingresso(1, TIPO_VIP, STATUS_DISPONIVEL);
        assertEquals(1, ingresso.getId());
    }
}
