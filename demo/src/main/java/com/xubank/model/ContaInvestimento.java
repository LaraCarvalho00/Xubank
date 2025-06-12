package com.xubank.model;

import jakarta.persistence.Entity;
import java.util.Random;

@Entity
public class ContaInvestimento extends Conta {

    private double rendimentoAcumulado = 0.0;

    @Override
    public void sacar(double valor) {
        // Regra: 22,5% de imposto sobre o rendimento no momento do saque.
        double imposto = this.rendimentoAcumulado * 0.225;

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
     * Aplica o rendimento mensal, que pode ser positivo ou negativo, e a taxa sobre o rendimento.
     */
    public void renderMensal() {
        // Rendimento varia entre -0,60 e 1,50%
        double taxaRendimento = -0.0060 + new Random().nextDouble() * (0.0150 - (-0.0060));
        double rendimentoBruto = this.saldo * taxaRendimento;

        if (rendimentoBruto > 0) {
            // Cobra 1% do rendimento mensal do cliente, caso o rendimento seja positivo.
            double taxaSobreRendimento = rendimentoBruto * 0.01;
            this.saldo += (rendimentoBruto - taxaSobreRendimento);
            this.rendimentoAcumulado += rendimentoBruto; // Acumula apenas rendimento positivo
        } else {
            // Se o rendimento for negativo, apenas debita do saldo
            this.saldo += rendimentoBruto;
        }
    }
}