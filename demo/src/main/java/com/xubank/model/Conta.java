package com.xubank.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operacao> operacoes = new ArrayList<>();

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);
    public abstract String getExtratoMensal();

    public Long getId() { return id; }

    public double getSaldo() { return saldo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Operacao> getOperacoes() { return operacoes; }
    public void setOperacoes(List<Operacao> operacoes) { this.operacoes = operacoes; }

    public void adicionarOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }
}
