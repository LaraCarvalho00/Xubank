package com.xubank.Entity;

import java.util.ArrayList;
import java.util.List;

import com.xubank.Dto.ClienteDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "cpf")
public class Cliente {

    @Id
    private String cpf;
    private String nome;
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Conta> contas = new ArrayList<>();

    public Cliente(ClienteDto clienteDto) {
        this.cpf = clienteDto.getCpf();
        this.nome = clienteDto.getNome();
        this.senha = clienteDto.getSenha();
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
        conta.setCliente(this);
    }

    public String ToString() {
        StringBuilder str = new StringBuilder();
        str.append("Cliente ID: ").append(cpf).append('\n');
        str.append("Cliente NOME: ").append(nome).append('\'');
        str.append("Contas: ");
        for (Conta conta : contas) {
            str.append("\n").append(conta.ToString());
        }
        return str.toString();
    }
}
