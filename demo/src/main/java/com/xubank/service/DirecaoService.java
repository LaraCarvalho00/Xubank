package com.xubank.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xubank.model.Cliente;
import com.xubank.model.Conta;
import com.xubank.repository.ClienteRepository;
import com.xubank.repository.ContaRepository;

@Service
public class DirecaoService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public DirecaoService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    // 1. Valor total em custódia por tipo de conta
    public Map<String, Double> totalPorTipoDeConta() {
        List<Conta> contas = contaRepository.findAll();
        Map<String, Double> totais = new HashMap<>();

        for (Conta conta : contas) {
            String tipo = conta.getClass().getSimpleName();
            totais.put(tipo, totais.getOrDefault(tipo, 0.0) + conta.getSaldo());
        }
        return totais;
    }

    // 2. Saldo médio das contas
    public double saldoMedioGeral() {
        List<Conta> contas = contaRepository.findAll();
        if (contas.isEmpty()) return 0;
        return contas.stream().mapToDouble(Conta::getSaldo).average().orElse(0);
    }

    // 3. Clientes extremos (maior e menor saldo total)
    public Map<String, Cliente> clienteComMaiorEMenorSaldo() {
        List<Cliente> clientes = clienteRepository.findAll();

        Cliente maior = null;
        Cliente menor = null;
        double maiorSaldo = Double.NEGATIVE_INFINITY;
        double menorSaldo = Double.POSITIVE_INFINITY;

        for (Cliente cliente : clientes) {
            double total = cliente.getContas()
                                  .stream()
                                  .mapToDouble(Conta::getSaldo)
                                  .sum();

            if (total > maiorSaldo) {
                maiorSaldo = total;
                maior = cliente;
            }
            if (total < menorSaldo) {
                menorSaldo = total;
                menor = cliente;
            }
        }

        Map<String, Cliente> result = new HashMap<>();
        result.put("maiorSaldo", maior);
        result.put("menorSaldo", menor);
        return result;
    }
}
