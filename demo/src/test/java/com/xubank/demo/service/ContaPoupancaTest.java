package com.xubank.demo.Service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xubank.Entity.ContaPoupanca;

public class ContaPoupancaTest {

    @Test
    void deveRenderJurosDe0_6Porcento() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.Depositar(1000);

        conta.RenderMensal();

        assertEquals(1006.0, conta.getSaldo(), 0.01);
    }

    @Test
    void deveSacarComSaldoSuficiente() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.Depositar(500);
        conta.Sacar(200);

        assertEquals(300, conta.getSaldo(), 0.01);
    }

    @Test
    void deveFalharAoSacarComSaldoInsuficiente() {
        ContaPoupanca conta = new ContaPoupanca();
        conta.Depositar(100);

        assertThrows(IllegalArgumentException.class, () -> conta.Sacar(200));
    }
}
