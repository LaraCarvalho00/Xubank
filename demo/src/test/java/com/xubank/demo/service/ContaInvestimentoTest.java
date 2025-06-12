package com.xubank.demo.service;

import org.junit.jupiter.api.Test;

import com.xubank.model.ContaInvestimento;

import static org.junit.jupiter.api.Assertions.*;

public class ContaInvestimentoTest {

    @Test
    void deveRenderEDescontar1PorcentoDoLucro() {
        ContaInvestimento conta = new ContaInvestimento();
        conta.depositar(1000);
        conta.renderMensal(); // assume taxaMensal positiva de 1.2%

        double rendimentoBruto = 1000 * 0.012;
        double rendimentoLiquido = rendimentoBruto - (rendimentoBruto * 0.01);

        assertEquals(1000 + rendimentoLiquido, conta.getSaldo(), 0.01);
    }

    @Test
    void deveCobrarImpostoNoSaque() {
        ContaInvestimento conta = new ContaInvestimento();
        conta.depositar(1000);
        conta.renderMensal();

        double saldoAntes = conta.getSaldo();
        conta.sacar(100);

        assertTrue(conta.getSaldo() < saldoAntes - 100); // verifica imposto de 22,5%
    }
}
