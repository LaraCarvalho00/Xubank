package com.xubank.model;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {

    @Override
    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            adicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        adicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    public void renderMensal() {
        saldo += saldo * 0.006;
    }

    @Override
    public String getExtratoMensal() {
        return "Extrato da Poupança: " + getOperacoes().size() + " operações.";
    }
}
