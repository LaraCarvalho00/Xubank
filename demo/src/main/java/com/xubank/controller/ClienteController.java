package com.xubank.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xubank.Dto.ClienteDto;
import com.xubank.Entity.Cliente;
import com.xubank.Interfaces.IClienteService;
import com.xubank.Service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> Cadastrar(@RequestBody ClienteDto cliente) {
        return ResponseEntity.ok(clienteService.CadastrarCliente(cliente));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> BuscarPorCpf(@PathVariable String cpf) {
        return clienteService.BuscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}