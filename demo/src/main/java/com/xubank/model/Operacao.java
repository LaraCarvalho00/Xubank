package com.xubank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    private LocalDateTime data;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Operacao() {}

    public Operacao(TipoOperacao tipo, double valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.data = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public TipoOperacao getTipo() { return tipo; }
    public void setTipo(TipoOperacao tipo) { this.tipo = tipo; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
}
