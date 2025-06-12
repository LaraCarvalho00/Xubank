package com.xubank.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xubank.model.Cliente;
import com.xubank.service.DirecaoService;

@RestController
@RequestMapping("/direcao")
public class DirecaoController {

    private final DirecaoService direcaoService;

    public DirecaoController(DirecaoService direcaoService) {
        this.direcaoService = direcaoService;
    }

    @GetMapping("/saldos")
    public ResponseEntity<Map<String, Double>> totalPorTipoDeConta() {
        return ResponseEntity.ok(direcaoService.totalPorTipoDeConta());
    }

    @GetMapping("/saldo-medio")
    public ResponseEntity<Double> saldoMedioGeral() {
        return ResponseEntity.ok(direcaoService.saldoMedioGeral());
    }

    @GetMapping("/clientes-extremos")
    public ResponseEntity<Map<String, Cliente>> clientesExtremos() {
        return ResponseEntity.ok(direcaoService.clienteComMaiorEMenorSaldo());
    }
}
