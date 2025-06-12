package com.xubank.demo.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xubank.Entity.ContaRendaFixa;

public class ContaRendaFixaTest {

    @Test
    void deveAplicarRendimentoMensal() {
        ContaRendaFixa conta = new ContaRendaFixa();
        conta.Depositar(1000);

        conta.RenderMensal(); 

        double esperado = 1000 * 1.008 - 20;
        assertEquals(esperado, conta.getSaldo(), 0.01);
    }

    @Test
    void deveDescontarImpostoNoSaque() {
        ContaRendaFixa conta = new ContaRendaFixa();
        conta.Depositar(1000);
        conta.RenderMensal();

        double saldoAntes = conta.getSaldo();
        conta.Sacar(100);

        assertTrue(conta.getSaldo() < saldoAntes - 100); 
    }
}
