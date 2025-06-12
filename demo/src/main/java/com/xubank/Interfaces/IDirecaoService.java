package com.xubank.Interfaces;

import java.util.Map;

import com.xubank.Entity.Cliente;

public interface IDirecaoService {
    
    public Map<String, Double> TotalPorTipoDeConta();

    public double SaldoMedioGeral();

    public Map<String, Cliente> ClienteComMaiorEMenorSaldo();

}
