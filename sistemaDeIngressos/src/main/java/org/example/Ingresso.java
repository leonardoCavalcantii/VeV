package org.example;

public class Ingresso {

    private int id;
    private String tipo;
    private String status;

    // Tipos válidos de ingresso
    public static final String TIPO_VIP = "VIP";
    public static final String TIPO_NORMAL = "NORMAL";
    public static final String TIPO_MEIA_ENTRADA = "MEIA_ENTRADA";

    // Status válidos
    public static final String STATUS_DISPONIVEL = "DISPONIVEL";
    public static final String STATUS_VENDIDO = "VENDIDO";

    public Ingresso(int id, String tipo, String status) {
        if (!tipoValido(tipo)) {
            throw new IllegalArgumentException("Tipo de ingresso inválid");
        }
        if (!isStatusValido(status)) {
            throw new IllegalArgumentException("Status de ingresso inválido");
        }

        this.id = id;
        this.tipo = tipo;
        this.status = status;
    }


    private boolean tipoValido(String tipo) {
        return TIPO_VIP.equals(tipo) || TIPO_NORMAL.equals(tipo) || TIPO_MEIA_ENTRADA.equals(tipo);
    }


    private boolean isStatusValido(String status) {
        return STATUS_DISPONIVEL.equals(status) || STATUS_VENDIDO.equals(status);
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
        if (STATUS_VENDIDO.equals(this.status)) {
            throw new RuntimeException("Este ingresso já está como vendido.");
        }
        this.status = STATUS_VENDIDO;
    }

    @Override
    public String toString() {
        return "Ingresso{id=" + id + ", tipo='" + tipo + "', status='" + status + "'}";
    }
}
