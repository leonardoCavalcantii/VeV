package org.example;

import java.time.LocalDate;

public class Fatura {
    private String cliente;
    private double valor;
    private LocalDate data;
    private String estado;

    public Fatura(String cliente, double valor, String data) {
        this.cliente = cliente;
        this.valor = valor;
        this.data = LocalDate.parse(data);
        this.estado = "PENDENTE";
    }

    public String getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public String getEstado() {
        return estado;
    }
}



