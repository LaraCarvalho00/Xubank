package com.xubank.demo.Service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xubank.Entity.ContaInvestimento;

public class ContaInvestimentoTest {

    @Test
    void deveRenderEDescontar1PorcentoDoLucro() {
        ContaInvestimento conta = new ContaInvestimento();
        conta.Depositar(1000);
        conta.RenderMensal(); 

        double rendimentoBruto = 1000 * 0.012;
        double rendimentoLiquido = rendimentoBruto - (rendimentoBruto * 0.01);

        assertEquals(1000 + rendimentoLiquido, conta.getSaldo(), 0.01);
    }

    @Test
    void deveCobrarImpostoNoSaque() {
        ContaInvestimento conta = new ContaInvestimento();
        conta.Depositar(1000);
        conta.RenderMensal();

        double saldoAntes = conta.getSaldo();
        conta.Sacar(100);

        assertTrue(conta.getSaldo() < saldoAntes - 100); 
    }
}
