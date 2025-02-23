import org.example.Ingresso;
import org.example.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabelaDeDecisaoSistemaDeIngressoTest {

    private static final String TIPO_NORMAL = "NORMAL";
    private static final String TIPO_VIP = "VIP";
    private static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";

    private static final String STATUS_DISPONIVEL = "DISPONIVEL";
    private static final String STATUS_VENDIDO = "VENDIDO";
    private static final String STATUS_INVALIDO = "INVALIDO";

    private static final double PRECO_VIP = 20.0;
    private static final double PRECO_NORMAL = 10.0;
    private static final double DESCONTO = 0.15;

    private Lote lote;
    private Ingresso ingresso;

    @BeforeEach
    void setup() {
        lote = new Lote(1, DESCONTO);
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_DISPONIVEL);
    }

    @Test
    void deveMarcarIngressoComoVendido() {
        ingresso.marcarComoVendido();
        assertEquals(STATUS_VENDIDO, ingresso.getStatus(), "O status do ingresso deveria ser 'VENDIDO'.");
    }

    @Test
    void naoDeveMarcarIngressoJaVendidoComoVendido() {
        ingresso = new Ingresso(1, TIPO_NORMAL, STATUS_VENDIDO);
        assertThrows(RuntimeException.class, ingresso::marcarComoVendido, "Deveria lançar exceção ao marcar um ingresso já vendido.");
    }

    @Test
    void naoDeveCriarIngressoComStatusInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Ingresso(1, TIPO_NORMAL, STATUS_INVALIDO),
                "Status de ingresso inválido deveria lançar exceção."
        );
    }

    @Test
    void naoDeveCriarIngressoComStatusNulo() {
        assertThrows(NullPointerException.class,
                () -> new Ingresso(1, TIPO_NORMAL, null),
                "Status nulo deveria lançar exceção de ponteiro nulo."
        );
    }
}
