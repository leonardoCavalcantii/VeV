package org.example;

public class Ingresso {

    private int id;
    private String tipo;
    private String status;

    public Ingresso(int id, String tipo, String status) {
        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void marcarComoVendido() {
        if(!"VENDIDO".equals(this.status)) {
            this.status = "VENDIDO";
        } else {
            throw new RuntimeException("já está como vendido");
        }
    }
}
