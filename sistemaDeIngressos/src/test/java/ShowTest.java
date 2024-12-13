import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShowTest {

    @Test
    void calcularReceitaLiquidaComDespesasECache() {

        Show show = new Show(1000.0, 2000.0, false); // Cachê R$ 1000, despesas R$ 2000, não é data especial

        show.adicionarIngressosVendidos("VIP", 100, 50.0); // 100 ingressos VIP a R$ 50,00
        show.adicionarIngressosVendidos("NORMAL", 300, 30.0); // 300 ingressos NORMAL a R$ 30,00

        // Calculando a receita líquida
        double receitaLiquida = show.calcularReceitaLiquida();

        assertEquals(12000.0, receitaLiquida, 0.01);
    }
}
