import org.example.Fatura;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class FaturaTest {

    //toda fatura por defalt eh iniciada como PENDENTE.
    //adicao de novos testes para checar funcionamento correto da criacao da fatura!
    @Test
    public void test01(){
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17" );
        assertEquals("Joao Silva", fatura.getCliente());
        assertEquals(1500.0, fatura.getValor(), 0.0001);
        assertEquals(LocalDate.of(2020, 2, 17), fatura.getData());
        assertEquals("PENDENTE", fatura.getEstado());

    }
}
