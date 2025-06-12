package com.xubank.DTO;

import com.xubank.model.TipoConta;

public class NovaContaDTO {
    private TipoConta tipo;
    private String clienteCpf; // Adicionado para identificar o cliente

    public TipoConta getTipo() { return tipo; }
    public void setTipo(TipoConta tipo) { this.tipo = tipo; }
    public String getClienteCpf() { return clienteCpf; }
    public void setClienteCpf(String clienteCpf) { this.clienteCpf = clienteCpf; }
}