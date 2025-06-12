package com.xubank.service;

import com.xubank.model.Cliente;
import com.xubank.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Cadastrar novo cliente
    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new IllegalArgumentException("Cliente com este CPF já existe.");
        }
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por CPF
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf);
    }
}