package com.xubank.demo.service;


import org.junit.jupiter.api.Test;

import com.xubank.model.ContaPoupanca;

import static org.junit.jupiter.api.Assertions.*;

public class ContaPoupancaTest {

    @Test
    void deveRenderJurosDe0_6Porcento() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.depositar(1000);

        conta.renderMensal();

        assertEquals(1006.0, conta.getSaldo(), 0.01);
    }

    @Test
    void deveSacarComSaldoSuficiente() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.depositar(500);
        conta.sacar(200);

        assertEquals(300, conta.getSaldo(), 0.01);
    }

    @Test
    void deveFalharAoSacarComSaldoInsuficiente() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.depositar(100);

        assertThrows(IllegalArgumentException.class, () -> conta.sacar(200));
    }
}
