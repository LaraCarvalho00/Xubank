package com.xubank.model;

import jakarta.persistence.Entity;
import java.util.Random;

@Entity
public class ContaInvestimento extends Conta {

    @Override
    public void sacar(double valor) {
        double rendimento = saldo * 0.012; // assume último rendimento positivo
        double imposto = rendimento * 0.225;
        saldo -= (valor + imposto);
        adicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        adicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    public void renderMensal() {
        double taxa = -0.006 + new Random().nextDouble() * (0.015 - (-0.006));
        double rendimento = saldo * taxa;

        if (rendimento > 0) {
            saldo += rendimento - (rendimento * 0.01);
        } else {
            saldo += rendimento;
        }
    }

    @Override
    public String getExtratoMensal() {
        return "Extrato da Conta Investimento: " + getOperacoes().size() + " operações.";
    }
}
