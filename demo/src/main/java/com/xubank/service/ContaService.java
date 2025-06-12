package com.xubank.service;

import com.xubank.model.*;
import com.xubank.repository.ContaRepository;

import org.springframework.stereotype.Service;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta depositar(Long idConta, double valor) {
        Conta conta = buscarContaOuFalhar(idConta);
        conta.depositar(valor);
        return contaRepository.save(conta);
    }

    public Conta sacar(Long idConta, double valor) {
        Conta conta = buscarContaOuFalhar(idConta);
        conta.sacar(valor);
        return contaRepository.save(conta);
    }

    public String extratoMensal(Long idConta) {
        Conta conta = buscarContaOuFalhar(idConta);
        return conta.getExtratoMensal();
    }

    public void aplicarRendimentoMensal(Long idConta) {
        Conta conta = buscarContaOuFalhar(idConta);
        if (conta instanceof ContaPoupanca cp) {
            cp.renderMensal();
        } else if (conta instanceof ContaRendaFixa rf) {
            rf.renderMensal();
        } else if (conta instanceof ContaInvestimento ci) {
            ci.renderMensal();
        }
        contaRepository.save(conta);
    }

    private Conta buscarContaOuFalhar(Long idConta) {
        return contaRepository.findById(idConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }
}
