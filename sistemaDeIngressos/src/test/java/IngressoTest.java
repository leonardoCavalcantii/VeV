import org.example.Ingresso;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class IngressoTest {

    private Ingresso ingresso;

    @Test
    void marcarIngressoComoVendido() {
        ingresso = new Ingresso(1, "NORMAL", "DISPONIVEL");
        ingresso.marcarComoVendido();
        assertEquals("VENDIDO", ingresso.getStatus());
    }

    @Test
    void naoMarcarIngressoComoVendido() {
        ingresso = new Ingresso(1, "NORMAL", "VENDIDO");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ingresso.marcarComoVendido();
        });

        assertEquals("já está como vendido", exception.getMessage());
    }
    
}
