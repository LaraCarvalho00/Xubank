package com.xubank.model;

import jakarta.persistence.Entity;
import java.util.Random;

@Entity
public class ContaRendaFixa extends Conta {

    private double rendimentoAcumulado = 0.0;

    @Override
    public void sacar(double valor) {
        // Regra: 15% de imposto sobre o rendimento no momento do saque.
        double imposto = this.rendimentoAcumulado * 0.15;

        if (getSaldo() < valor + imposto) {
            throw new IllegalArgumentException("Saldo insuficiente para cobrir o saque e os impostos sobre o rendimento.");
        }

        this.saldo -= (valor + imposto);
        this.rendimentoAcumulado = 0; // O rendimento foi taxado, então o acumulado zera.
        adicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
    }

    @Override
    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        this.saldo += valor;
        adicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    /**
     * Aplica o rendimento mensal e a taxa fixa.
     */
    public void renderMensal() {
        // Rendimento varia entre 0,50 e 0,85%
        double taxaRendimento = 0.0050 + new Random().nextDouble() * (0.0085 - 0.0050);
        double rendimentoBruto = this.saldo * taxaRendimento;

        this.saldo += rendimentoBruto;
        this.rendimentoAcumulado += rendimentoBruto; // Acumula o rendimento para futura tributação

        // Cobra R$20 do cliente, mensalmente
        this.saldo -= 20;
    }
}
