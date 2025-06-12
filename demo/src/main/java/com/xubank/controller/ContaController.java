package com.xubank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xubank.model.Conta;
import com.xubank.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestParam double valor) {
        try {
            Conta conta = contaService.depositar(id, valor);
            return ResponseEntity.ok(conta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestParam double valor) {
        try {
            Conta conta = contaService.sacar(id, valor);
            return ResponseEntity.ok(conta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<String> extrato(@PathVariable Long id) {
        try {
            String extrato = contaService.extratoMensal(id);
            return ResponseEntity.ok(extrato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/rendimento")
    public ResponseEntity<Void> aplicarRendimento(@PathVariable Long id) {
        try {
            contaService.aplicarRendimentoMensal(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
