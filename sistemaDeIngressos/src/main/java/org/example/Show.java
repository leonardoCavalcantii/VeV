package org.example;

public class Show {
    private double cache;
    private double despesasInfraestrutura;
    private boolean dataEspecial;
    private double totalIngressosVendidos;

    public Show(double cache, double despesasInfraestrutura, boolean dataEspecial) {
        this.cache = cache;
        this.despesasInfraestrutura = despesasInfraestrutura;
        this.dataEspecial = dataEspecial;
        this.totalIngressosVendidos = 0.0;
    }

    public void adicionarIngressosVendidos(String tipo, int quantidade, double preco) {
        double precoFinal = preco;

        // Definir o preço base para VIP ou MEIA_ENTRADA
        if ("VIP".equals(tipo)) {
            precoFinal = preco * 2; // O preço do ingresso VIP é o dobro do NORMAL
        } else if ("MEIA_ENTRADA".equals(tipo)) {
            precoFinal = preco / 2; // O preço do ingresso MEIA_ENTRADA é a metade do NORMAL
        }

        // Aplicar o desconto de 15% apenas para VIP e MEIA_ENTRADA
        if ("VIP".equals(tipo) || "MEIA_ENTRADA".equals(tipo)) {
            precoFinal -= precoFinal * 0.15; // Desconto de 15% para VIP e MEIA_ENTRADA
        }

        // Adicionar o valor total de ingressos vendidos ao total
        totalIngressosVendidos += quantidade * precoFinal;
    }


    public double calcularReceitaLiquida() {
        if (dataEspecial) {
            despesasInfraestrutura *= 1.15; // 15% a mais para data especial
        }

        System.out.println("Total Ingressos Vendidos: " + totalIngressosVendidos);
        System.out.println("Despesas Infraestrutura: " + despesasInfraestrutura);
        System.out.println("Cache: " + cache);

        double receitaLiquida = totalIngressosVendidos - despesasInfraestrutura - cache;
        System.out.println("Receita Líquida: " + receitaLiquida);

        return receitaLiquida;
    }

        public String statusFinanceiro() {
        double receitaLiquida = calcularReceitaLiquida();
        if (receitaLiquida > 0) {
            return "LUCRO";
        } else if (receitaLiquida == 0) {
            return "ESTÁVEL";
        } else {
            return "PREJUÍZO";
        }
    }
}
