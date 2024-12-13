
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

import org.example.Ingresso;
import org.junit.jupiter.api.Test;

public class IngressoTest {

    @Test
    void marcarIngressoComoVendido() {
        Ingresso ingresso = new Ingresso(1, "NORMAL", "DISPONIVEL");
        ingresso.marcarComoVendido();
        assertEquals("VENDIDO", ingresso.getStatus());
    }
    
}
