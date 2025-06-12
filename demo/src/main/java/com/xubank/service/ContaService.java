package com.xubank.service;

import com.xubank.model.*;
import com.xubank.repository.ClienteRepository;
import com.xubank.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }
    
    @Transactional
    public Conta criarConta(String clienteCpf, TipoConta tipoConta) {
        Cliente cliente = clienteRepository.findById(clienteCpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com CPF " + clienteCpf + " não encontrado"));

        Conta novaConta;
        switch (tipoConta) {
            case CORRENTE -> novaConta = new ContaCorrente();
            case POUPANCA -> novaConta = new ContaPoupanca();
            case RENDA_FIXA -> novaConta = new ContaRendaFixa();
            case INVESTIMENTO -> novaConta = new ContaInvestimento();
            default -> throw new IllegalArgumentException("Tipo de conta inválido: " + tipoConta);
        }

        novaConta.setCliente(cliente);
        return contaRepository.save(novaConta);
    }

    @Transactional
    public Conta depositar(Long idConta, double valor) {
        Conta conta = buscarContaOuFalhar(idConta);
        conta.depositar(valor);
        return contaRepository.save(conta);
    }

    @Transactional
    public Conta sacar(Long idConta, double valor) {
        Conta conta = buscarContaOuFalhar(idConta);
        conta.sacar(valor);
        return contaRepository.save(conta);
    }

    public String extratoMensal(Long idConta) {
        Conta conta = buscarContaOuFalhar(idConta);
        return conta.getExtratoMensal();
    }

    @Transactional
    public void aplicarRendimentoMensal(Long idConta) {
        Conta conta = buscarContaOuFalhar(idConta);
        if (conta instanceof ContaPoupanca cp) {
            cp.renderMensal();
        } else if (conta instanceof ContaRendaFixa rf) {
            rf.renderMensal();
        } else if (conta instanceof ContaInvestimento ci) {
            ci.renderMensal();
        } else {
            throw new UnsupportedOperationException("Esta conta não possui rendimento.");
        }
        contaRepository.save(conta);
    }

    private Conta buscarContaOuFalhar(Long idConta) {
        return contaRepository.findById(idConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta " + idConta + " não encontrada"));
    }
}