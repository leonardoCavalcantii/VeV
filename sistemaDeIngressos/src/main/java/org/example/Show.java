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

    // Método para adicionar ingressos vendidos, considerando descontos
    public void adicionarIngressosVendidos(String tipo, int quantidade, double preco) {
        double precoFinal = preco;

        // Calculando preço de VIP e MEIA_ENTRADA antes do desconto
        if ("VIP".equals(tipo)) {
            precoFinal = preco * 2; // O preço do ingresso VIP é o dobro do NORMAL
        } else if ("MEIA_ENTRADA".equals(tipo)) {
            precoFinal = preco / 2; // O preço do ingresso MEIA_ENTRADA é a metade do NORMAL
        }

        // Aplicando o desconto de 15% para VIP e MEIA_ENTRADA
        if ("VIP".equals(tipo) || "MEIA_ENTRADA".equals(tipo)) {
            precoFinal -= precoFinal * 0.15; // Desconto de 15%
        }

        totalIngressosVendidos += quantidade * precoFinal; // Total de ingressos vendidos
    }

    // Método para calcular a receita líquida
    public double calcularReceitaLiquida() {
        if (dataEspecial) {
            despesasInfraestrutura *= 1.15; // 15% a mais para data especial
        }
        return totalIngressosVendidos - despesasInfraestrutura - cache; // Receita líquida //400-500-1000
    }
}
