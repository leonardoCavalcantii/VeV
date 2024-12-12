import org.junit.jupiter.api.Test;

import main.java.org.example.Ingresso;

public class IngressoTest {

    @Test
    void marcarIngressoComoVendido() {
        Ingresso ingresso = new Ingresso(1, "NORMAL", "DISPONIVEL");
        ingresso.marcarComoVendido();
        assertEquals("VENDIDO", ingresso.getStatus());
    }
    
}
