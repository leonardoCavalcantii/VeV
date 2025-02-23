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

        switch (tipo) {
            case "MEIA_ENTRADA":
                return preco / 2;
            case "VIP":
            case "NORMAL":
                return preco - (preco * desconto);
            default:
                throw new IllegalArgumentException("Tipo de ingresso inválido: " + tipo);
        }
    }
}
