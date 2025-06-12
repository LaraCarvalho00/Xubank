package com.xubank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xubank.model.Cliente;
import com.xubank.model.Conta;
import com.xubank.model.ContaCorrente;
import com.xubank.model.ContaInvestimento;
import com.xubank.model.ContaPoupanca;
import com.xubank.model.ContaRendaFixa;
import com.xubank.model.TipoConta;
import com.xubank.repository.ClienteRepository;
import com.xubank.repository.ContaRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;

    public ClienteService(ClienteRepository clienteRepository, ContaRepository contaRepository) {
        this.clienteRepository = clienteRepository;
        this.contaRepository = contaRepository;
    }

    // Cadastrar novo cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por CPF
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf);
    }

    // Criar nova conta para o cliente
    public Conta criarConta(String cpf, TipoConta tipoConta) {
        Cliente cliente = buscarPorCpf(cpf)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Conta novaConta;

        switch (tipoConta) {
            case CORRENTE -> novaConta = new ContaCorrente();
            case POUPANCA -> novaConta = new ContaPoupanca();
            case RENDA_FIXA -> novaConta = new ContaRendaFixa();
            case INVESTIMENTO -> novaConta = new ContaInvestimento();
            default -> throw new IllegalArgumentException("Tipo de conta inválido");
        }

        novaConta.setCliente(cliente);
        Conta contaSalva = contaRepository.save(novaConta);

        cliente.getContas().add(contaSalva);
        clienteRepository.save(cliente);

        return contaSalva;
    }
}
