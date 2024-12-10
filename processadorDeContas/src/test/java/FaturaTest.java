import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class FaturaTest {

    //toda fatura por defalt eh iniciada como PENDENTE.
    @Test
    public void test01(){
        Fatura fatura = new Fatura("Joao Silva", 1500.0, "2020-02-17" );
        assertEquals("PENDENTE", fatura.getEstado(), "faturas sao PENDENTES ate que sejem pagas!");
    }
}
