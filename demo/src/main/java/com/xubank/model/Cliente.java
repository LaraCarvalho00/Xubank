package com.xubank.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    private String cpf;

    private String nome;
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas;

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<Conta> getContas() { return contas; }
    public void setContas(List<Conta> contas) { this.contas = contas; }
}
