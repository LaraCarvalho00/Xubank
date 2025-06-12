package com.xubank.service;

import com.xubank.model.Cliente;
import com.xubank.model.Conta;
import com.xubank.repository.ClienteRepository;
import com.xubank.repository.ContaRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirecaoService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public DirecaoService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Map<String, Double> totalPorTipoDeConta() {
        return contaRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        conta -> conta.getClass().getSimpleName(),
                        Collectors.summingDouble(Conta::getSaldo)
                ));
    }

    public double saldoMedioGeral() {
        return contaRepository.findAll().stream()
                .mapToDouble(Conta::getSaldo)
                .average()
                .orElse(0.0);
    }

    public Map<String, Cliente> clienteComMaiorEMenorSaldo() {
        Map<String, Cliente> result = new HashMap<>();

        Comparator<Cliente> comparadorPorSaldo = Comparator.comparingDouble(cliente ->
                cliente.getContas().stream().mapToDouble(Conta::getSaldo).sum());

        clienteRepository.findAll().stream().max(comparadorPorSaldo)
                .ifPresent(cliente -> result.put("clienteMaiorSaldo", cliente));

        clienteRepository.findAll().stream().min(comparadorPorSaldo)
                .ifPresent(cliente -> result.put("clienteMenorSaldo", cliente));

        return result;
    }
}