package com.xubank.Entity;

import java.util.Random;

import com.xubank.Enums.TipoOperacao;

import jakarta.persistence.Entity;

@Entity
public class ContaInvestimento extends Conta {

    private double rendimentoAcumulado = 0.0;

    @Override
    public void Sacar(double valor) {

        double imposto = this.rendimentoAcumulado * 0.225;

        if (getSaldo() < valor + imposto) {
            throw new IllegalArgumentException(
                    "Saldo insuficiente para cobrir o saque e os impostos sobre o rendimento.");
        }

        this.saldo -= (valor + imposto);
        this.rendimentoAcumulado = 0;
        AdicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
    }

    @Override
    public void Depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }
        this.saldo += valor;
        AdicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    public void RenderMensal() {
        double taxaRendimento = -0.0060 + new Random().nextDouble() * (0.0150 - (-0.0060));
        double rendimentoBruto = this.saldo * taxaRendimento;

        if (rendimentoBruto > 0) {
            double taxaSobreRendimento = rendimentoBruto * 0.01;
            this.saldo += (rendimentoBruto - taxaSobreRendimento);
            this.rendimentoAcumulado += rendimentoBruto;
        } else {
            this.saldo += rendimentoBruto;
        }
    }
}