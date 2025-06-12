package com.xubank.demo.service;

import org.junit.jupiter.api.Test;

import com.xubank.model.ContaRendaFixa;

import static org.junit.jupiter.api.Assertions.*;

public class ContaRendaFixaTest {

    @Test
    void deveAplicarRendimentoMensal() {
        ContaRendaFixa conta = new ContaRendaFixa();
        conta.depositar(1000);

        conta.renderMensal(); // Aplica rendimento e -20 taxa

        double esperado = 1000 * 1.008 - 20;
        assertEquals(esperado, conta.getSaldo(), 0.01);
    }

    @Test
    void deveDescontarImpostoNoSaque() {
        ContaRendaFixa conta = new ContaRendaFixa();
        conta.depositar(1000);
        conta.renderMensal();

        double saldoAntes = conta.getSaldo();
        conta.sacar(100);

        assertTrue(conta.getSaldo() < saldoAntes - 100); // verifica desconto de imposto
    }
}
