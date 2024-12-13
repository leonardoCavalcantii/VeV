import org.example.Show;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShowTest {

    @Test
    void calcularReceitaLiquidaComDespesasECache() {
        // Criando show
        Show show = new Show(1000.0, 2000.0, false);

        // Adicionando ingressos (supondo que a receita de ingressos foi gerada)
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);

        // Calculando a receita líquida
        double receitaLiquida = show.calcularReceitaLiquida();
        assertEquals(14500, receitaLiquida, 0.01);
    }

    @Test
    void verificarStatusFinanceiro() {
        Show show = new Show(3000.0, 2000.0, false); // Cachê R$ 3000, despesas R$ 2000, não é data especial
        show.adicionarIngressosVendidos("VIP", 100, 50.0);
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0);

        // A receita líquida será 12500.0, o que resulta em "LUCRO"
        String status = show.statusFinanceiro();
        assertEquals("LUCRO", status);

        // Testando com um show com receita líquida zero
        Show show2 = new Show(8750.0, 8750.0, false); // Cachê R$ 5000, despesas R$ 5000
        show2.adicionarIngressosVendidos("VIP", 100, 50.0);
        show2.adicionarIngressosVendidos("NORMAL", 300, 30.0);

        // A receita líquida será 0, então o status é "ESTÁVEL"
        status = show2.statusFinanceiro();
        assertEquals("ESTÁVEL", status);

        // Testando com um show com prejuízo
        Show show3 = new Show(10000.0, 5000.0, false); // Cachê R$ 10000, despesas R$ 5000
        show3.adicionarIngressosVendidos("VIP", 100, 50.0);
        show3.adicionarIngressosVendidos("NORMAL", 300, 30.0);

        // A receita líquida será 2500.0, então o status é "LUCRO"
        status = show3.statusFinanceiro();
        assertEquals("LUCRO", status);
    }


}
