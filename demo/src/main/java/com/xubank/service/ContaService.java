package com.xubank.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xubank.Dto.NovaContaDto;
import com.xubank.Entity.Cliente;
import com.xubank.Entity.Conta;
import com.xubank.Entity.ContaCorrente;
import com.xubank.Entity.ContaInvestimento;
import com.xubank.Entity.ContaPoupanca;
import com.xubank.Entity.ContaRendaFixa;
import com.xubank.Interfaces.IContaService;
import com.xubank.Repository.ClienteRepository;
import com.xubank.Repository.ContaRepository;

@Service
public class ContaService implements IContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public String CriarConta(NovaContaDto conta) {
        Cliente cliente = clienteRepository.findById(conta.getClienteCpf())
                .orElseThrow(() -> new IllegalArgumentException("Cliente com CPF " + conta.getClienteCpf() + " não encontrado"));

        Conta novaConta;
        switch (conta.getTipo().toString().toUpperCase()) {
            case "CORRENTE" -> novaConta = new ContaCorrente();
            case "POUPANCA" -> novaConta = new ContaPoupanca();
            case "RENDA_FIXA" -> novaConta = new ContaRendaFixa();
            case "INVESTIMENTO" -> novaConta = new ContaInvestimento();
            default -> throw new IllegalArgumentException("Tipo de conta inválido: " + conta.getTipo());
        }

        cliente.addConta(novaConta);
        contaRepository.save(novaConta);

        return cliente.ToString();
    }

    @Transactional
    public Conta Depositar(Long idConta, double valor) {
        Conta conta = BuscarContaOuFalhar(idConta);
        conta.Depositar(valor);
        return contaRepository.save(conta);
    }

    @Transactional
    public Conta Sacar(Long idConta, double valor) {
        Conta conta = BuscarContaOuFalhar(idConta);
        conta.Sacar(valor);
        return contaRepository.save(conta);
    }

    public String ExtratoMensal(Long idConta) {
        Conta conta = BuscarContaOuFalhar(idConta);
        return conta.GetExtratoMensal();
    }

    @Transactional
    public void AplicarRendimentoMensal(Long idConta) {
        Conta conta = BuscarContaOuFalhar(idConta);
        if (conta instanceof ContaPoupanca cp) {
            cp.RenderMensal();
        } else if (conta instanceof ContaRendaFixa rf) {
            rf.RenderMensal();
        } else if (conta instanceof ContaInvestimento ci) {
            ci.RenderMensal();
        } else {
            throw new UnsupportedOperationException("Esta conta não possui rendimento.");
        }
        contaRepository.save(conta);
    }

    private Conta BuscarContaOuFalhar(Long idConta) {
        return contaRepository.findById(idConta)
                .orElseThrow(() -> new IllegalArgumentException("Conta " + idConta + " não encontrada"));
    }
}