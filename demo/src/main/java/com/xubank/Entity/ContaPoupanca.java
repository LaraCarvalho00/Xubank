package com.xubank.Entity;

import com.xubank.Enums.TipoOperacao;

import jakarta.persistence.Entity;

@Entity
public class ContaPoupanca extends Conta {

    @Override
    public void Sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            AdicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
        } else {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    @Override
    public void Depositar(double valor) {
        saldo += valor;
        AdicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    public void RenderMensal() {
        saldo += saldo * 0.006;
    }

    @Override
    public String GetExtratoMensal() {
        return "Extrato da Poupança: " + getOperacoes().size() + " operações.";
    }
}
