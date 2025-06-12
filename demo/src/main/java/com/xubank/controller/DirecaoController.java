package com.xubank.Controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xubank.Entity.Cliente;
import com.xubank.Interfaces.IDirecaoService;
import com.xubank.Service.DirecaoService;

@RestController
@RequestMapping("/direcao")
public class DirecaoController {

    private final IDirecaoService direcaoService;

    public DirecaoController(DirecaoService direcaoService) {
        this.direcaoService = direcaoService;
    }

    @GetMapping("/saldos")
    public ResponseEntity<Map<String, Double>> totalPorTipoDeConta() {
        return ResponseEntity.ok(direcaoService.TotalPorTipoDeConta());
    }

    @GetMapping("/saldo-medio")
    public ResponseEntity<Double> saldoMedioGeral() {
        return ResponseEntity.ok(direcaoService.SaldoMedioGeral());
    }

    @GetMapping("/clientes-extremos")
    public ResponseEntity<Map<String, Cliente>> clientesExtremos() {
        return ResponseEntity.ok(direcaoService.ClienteComMaiorEMenorSaldo());
    }
}
