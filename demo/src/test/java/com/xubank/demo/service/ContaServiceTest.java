package com.xubank.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.xubank.Entity.Conta;
import com.xubank.Entity.ContaCorrente;
import com.xubank.Repository.ClienteRepository;
import com.xubank.Repository.ContaRepository;
import com.xubank.Service.ContaService;

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
        contaExistente.Depositar(100);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaExistente));
        when(contaRepository.save(any(Conta.class))).thenReturn(contaExistente);

        Conta contaAtualizada = contaService.Depositar(1L, 50);

        assertEquals(150.0, contaAtualizada.getSaldo());
    }

    @Test
    void deveSacarDaContaComSucesso() {
        ContaCorrente contaExistente = new ContaCorrente();
        contaExistente.Depositar(200);

        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaExistente));
        when(contaRepository.save(any(Conta.class))).thenReturn(contaExistente);

        Conta contaAtualizada = contaService.Sacar(1L, 70);

        assertEquals(130.0, contaAtualizada.getSaldo());
    }

    @Test
    void deveLancarErroSeContaNaoExisteAoDepositar() {
        when(contaRepository.findById(99L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            contaService.Depositar(99L, 100);
        });

        String mensagemEsperada = "Conta com ID 99 não encontrada";
        assertEquals(mensagemEsperada, ex.getMessage());
    }
}
