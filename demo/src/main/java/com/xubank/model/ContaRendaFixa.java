package com.xubank.model;

import jakarta.persistence.Entity;
import java.util.Random;

@Entity
public class ContaRendaFixa extends Conta {

    @Override
    public void sacar(double valor) {
        double rendimento = saldo * 0.008; // usa a última taxa aplicada
        double imposto = rendimento * 0.15;
        saldo -= (valor + imposto);
        adicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        adicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    public void renderMensal() {
        double taxa = 0.005 + new Random().nextDouble() * (0.0085 - 0.005);
        saldo += saldo * taxa;
        saldo -= 20;
    }

    @Override
    public String getExtratoMensal() {
        return "Extrato da Renda Fixa: " + getOperacoes().size() + " operações.";
    }
}
