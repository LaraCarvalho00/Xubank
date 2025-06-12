package com.xubank.controller;

import com.xubank.DTO.NovaContaDTO;
import com.xubank.model.Cliente;
import com.xubank.model.Conta;
import com.xubank.service.ClienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(cliente));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    
@PostMapping("/{cpf}/contas")
public ResponseEntity<Conta> criarConta(@PathVariable String cpf, @RequestBody NovaContaDTO dto) {
    try {
        Conta nova = clienteService.criarConta(cpf, dto.getTipo());
        return ResponseEntity.ok(nova);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}
}
