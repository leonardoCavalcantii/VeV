package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fatura {
    private String cliente;
    private double valor;
    private LocalDate data;
    private String estado;
    private List<Pagamento> pagamentos;

    public Fatura(String cliente, double valor, String data) {
        this.cliente = cliente;
        this.valor = valor;
        this.data = LocalDate.parse(data);
        this.estado = "PENDENTE";
        this.pagamentos = new ArrayList<>();
    }

    public void adicionarPagamento(Pagamento pagamento) {
        if (pagamento.getTipo().equals("BOLETO")) {
            //valores limites do boleto
            if (pagamento.getValor() < 0.01 || pagamento.getValor() > 5000.0) {
                return;
            }
            //boleto com atraso
            if (pagamento.getData().isAfter(this.data)) {
                pagamento.setValor(pagamento.getValor() * 1.10);
            }
        }
        if (pagamento.getTipo().equals("CARTAO")) {
            //15 dias do cartao
            java.time.LocalDate dataLimiteCartao = this.data.minusDays(15);
            if (pagamento.getData().isBefore(dataLimiteCartao)) {
                return;
            }
        }
        pagamentos.add(pagamento);
    }

    //ok!
    public double getSomaPagamentos() {
        return pagamentos.stream().mapToDouble(Pagamento::getValor).sum();
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
        if (getSomaPagamentos() >= getValor())
            estado = "PAGA";
        return estado;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
}




