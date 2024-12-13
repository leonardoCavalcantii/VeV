package org.example;

public class Lote {
    private int id;
    private double desconto;

    public Lote(int id, double desconto) {
        this.id = id;
        this.desconto = desconto;
    }

    public int getId() {
        return this.id;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double calcularPrecoFinal(String tipo, double preco) {
        if (tipo == null || preco < 0) {
            throw new IllegalArgumentException("Tipo de ingresso ou preço inválido");
        }

        if ("MEIA_ENTRADA".equals(tipo)) {
            return preco; //Não tem desconto para meia
        }
        else {
            return preco - (preco * desconto); //aplica desconto para vip e normal
        }
    }
}
