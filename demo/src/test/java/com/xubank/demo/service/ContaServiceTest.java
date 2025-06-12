package com.xubank.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.xubank.model.Conta;
import com.xubank.model.ContaCorrente;
import com.xubank.repository.ContaRepository;
import com.xubank.service.ContaService;

class ContaServiceTest {

    private ContaService contaService;
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
        contaRepository = mock(ContaRepository.class);
        contaService = new ContaService(contaRepository);
    }

    @Test
    void deveDepositarNaConta() {
        ContaCorrente conta = new ContaCorrente();
        conta.depositar(100);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(conta)).thenReturn(conta);

        Conta result = contaService.depositar(1L, 50);
        assertEquals(150, result.getSaldo(), 0.01);
        assertEquals(2, result.getOperacoes().size()); // 1 do setUp, 1 do depósito
    }

    @Test
    void deveSacarDaConta() {
        ContaCorrente conta = new ContaCorrente();
        conta.depositar(200);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(conta)).thenReturn(conta);

        Conta result = contaService.sacar(1L, 100);
        assertEquals(100, result.getSaldo(), 0.01);
        assertEquals(2, result.getOperacoes().size()); // 1 do depósito, 1 do saque
    }

    @Test
    void deveLancarErroSeContaNaoExiste() {
        when(contaRepository.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            contaService.depositar(99L, 100);
        });

        assertEquals("Conta não encontrada", ex.getMessage());
    }
}
