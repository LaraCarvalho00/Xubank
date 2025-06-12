package com.xubank.Entity;

import com.xubank.Enums.TipoOperacao;

import jakarta.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {

    private double limite = 100.0;

    @Override
    public void Sacar(double valor) {
        if (saldo + limite >= valor) {
            saldo -= valor;
            AdicionarOperacao(new Operacao(TipoOperacao.SAQUE, valor, this));
        } else {
            throw new IllegalArgumentException("Limite insuficiente");
        }
    }

    @Override
    public void Depositar(double valor) {
        if (saldo < 0) {
            double taxa = Math.abs(saldo) * 0.03 + 10;
            saldo += (valor - taxa);
        } else {
            saldo += valor;
        }
        AdicionarOperacao(new Operacao(TipoOperacao.DEPOSITO, valor, this));
    }

    @Override
    public String GetExtratoMensal() {
        return "Extrato da Conta Corrente: " + getOperacoes().size() + " operações.";
    }
}
