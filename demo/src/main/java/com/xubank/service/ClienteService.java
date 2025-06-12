package com.xubank.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.xubank.Dto.ClienteDto;
import com.xubank.Entity.Cliente;
import com.xubank.Interfaces.IClienteService;
import com.xubank.Repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente CadastrarCliente(ClienteDto cliente) {
        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new IllegalArgumentException("Cliente com este CPF já existe.");
        }

        Cliente novoCliente = new Cliente(cliente);

        return clienteRepository.save(novoCliente);
    }
    public Optional<Cliente> BuscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf);
    }
}