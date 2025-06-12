package com.xubank.controller;

import com.xubank.DTO.NovaContaDTO;
import com.xubank.DTO.OperacaoDTO;
import com.xubank.model.Conta;
import com.xubank.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody NovaContaDTO dto) {
        Conta novaConta = contaService.criarConta(dto.getClienteCpf(), dto.getTipo());
        return ResponseEntity.ok(novaConta);
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody OperacaoDTO dto) {
        Conta conta = contaService.depositar(id, dto.getValor());
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody OperacaoDTO dto) {
        Conta conta = contaService.sacar(id, dto.getValor());
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<String> extrato(@PathVariable Long id) {
        String extrato = contaService.extratoMensal(id);
        return ResponseEntity.ok(extrato);
    }

    @PostMapping("/{id}/rendimento")
    public ResponseEntity<Void> aplicarRendimento(@PathVariable Long id) {
        contaService.aplicarRendimentoMensal(id);
        return ResponseEntity.ok().build();
    }
}