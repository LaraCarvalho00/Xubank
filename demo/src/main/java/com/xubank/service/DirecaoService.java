package com.xubank.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.xubank.Entity.Cliente;
import com.xubank.Entity.Conta;
import com.xubank.Interfaces.IDirecaoService;
import com.xubank.Repository.ClienteRepository;
import com.xubank.Repository.ContaRepository;

@Service
public class DirecaoService implements IDirecaoService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public DirecaoService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Map<String, Double> TotalPorTipoDeConta() {
        return contaRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        conta -> conta.getClass().getSimpleName(),
                        Collectors.summingDouble(Conta::getSaldo)
                ));
    }

    public double SaldoMedioGeral() {
        return contaRepository.findAll().stream()
                .mapToDouble(Conta::getSaldo)
                .average()
                .orElse(0.0);
    }

    public Map<String, Cliente> ClienteComMaiorEMenorSaldo() {
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