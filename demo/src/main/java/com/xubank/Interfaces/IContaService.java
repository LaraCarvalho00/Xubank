package com.xubank.Interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.xubank.Dto.NovaContaDto;
import com.xubank.Entity.Conta;

public interface IContaService {

    @Transactional
    public String CriarConta(NovaContaDto conta);

    @Transactional
    public Conta Depositar(Long idConta, double valor);

    @Transactional
    public Conta Sacar(Long idConta, double valor);

    public String ExtratoMensal(Long idConta);

    @Transactional
    public void AplicarRendimentoMensal(Long idConta);
}
