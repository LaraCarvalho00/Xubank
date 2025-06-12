package com.xubank.Interfaces;

import java.util.Optional;

import com.xubank.Dto.ClienteDto;
import com.xubank.Entity.Cliente;

public interface IClienteService {

    public Cliente CadastrarCliente(ClienteDto cliente);

    public Optional<Cliente> BuscarPorCpf(String cpf);
}
