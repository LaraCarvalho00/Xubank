package com.xubank.demo.service;

import com.xubank.model.Conta;
import com.xubank.model.ContaCorrente;
import com.xubank.repository.ClienteRepository;
import com.xubank.repository.ContaRepository;
import com.xubank.service.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ContaServiceTest {

    private ContaService contaService;
    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        contaRepository = Mockito.mock(ContaRepository.class);
        clienteRepository = Mockito.mock(ClienteRepository.class);
        contaService = new ContaService(contaRepository, clienteRepository);
    }

    @Test
    void deveDepositarNaContaComSucesso() {
        ContaCorrente contaExistente = new ContaCorrente();
        contaExistente.depositar(100);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaExistente));
        when(contaRepository.save(any(Conta.class))).thenReturn(contaExistente);

        Conta contaAtualizada = contaService.depositar(1L, 50);

        assertEquals(150.0, contaAtualizada.getSaldo());
    }

    @Test
    void deveSacarDaContaComSucesso() {
        ContaCorrente contaExistente = new ContaCorrente();
        contaExistente.depositar(200);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaExistente));
        when(contaRepository.save(any(Conta.class))).thenReturn(contaExistente);

        Conta contaAtualizada = contaService.sacar(1L, 70);

        assertEquals(130.0, contaAtualizada.getSaldo());
    }

    @Test
    void deveLancarErroSeContaNaoExisteAoDepositar() {
        when(contaRepository.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            contaService.depositar(99L, 100);
        });

        String mensagemEsperada = "Conta com ID 99 não encontrada";
        assertEquals(mensagemEsperada, ex.getMessage());
    }
}
