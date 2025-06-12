package com.xubank.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xubank.Dto.NovaContaDto;
import com.xubank.Dto.OperacaoDto;
import com.xubank.Entity.Conta;
import com.xubank.Interfaces.IContaService;
import com.xubank.Service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final IContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<String> CriarConta(@RequestBody NovaContaDto conta) {
        String cliente = contaService.CriarConta(conta);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/{id}/deposito")
    public ResponseEntity<Conta> Depositar(@PathVariable Long id, @RequestBody OperacaoDto operacao) {
        Conta conta = contaService.Depositar(id, operacao.getValor());
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<Conta> Sacar(@PathVariable Long id, @RequestBody OperacaoDto operacao) {
        Conta conta = contaService.Sacar(id, operacao.getValor());
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<String> Extrato(@PathVariable Long id) {
        String extrato = contaService.ExtratoMensal(id);
        return ResponseEntity.ok(extrato);
    }

    @PostMapping("/{id}/rendimento")
    public ResponseEntity<Void> AplicarRendimento(@PathVariable Long id) {
        contaService.AplicarRendimentoMensal(id);
        return ResponseEntity.ok().build();
    }
}