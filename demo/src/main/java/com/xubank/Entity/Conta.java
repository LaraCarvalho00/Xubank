package com.xubank.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    private Cliente cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operacao> operacoes = new ArrayList<>();

    public abstract void Sacar(double valor);
    public abstract void Depositar(double valor);

    public String GetExtratoMensal() {
        LocalDateTime umMesAtras = LocalDateTime.now().minusMonths(1);

        List<Operacao> operacoesDoMes = this.operacoes.stream()
                .filter(op -> op.getData().isAfter(umMesAtras))
                .collect(Collectors.toList());

        if (operacoesDoMes.isEmpty()) {
            return "Nenhuma operação registrada no último mês.";
        }

        StringBuilder extrato = new StringBuilder("--- EXTRATO DO ÚLTIMO MÊS ---\n");
        operacoesDoMes.forEach(op -> {
            extrato.append(String.format("%s - %-10s - R$ %.2f\n",
                    op.getData().toLocalDate(),
                    op.getTipo(),
                    op.getValor()));
        });
        extrato.append("----------------------------\n");
        extrato.append(String.format("SALDO ATUAL: R$ %.2f\n", this.saldo));

        return extrato.toString();
    }

    public void AdicionarOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }

    public String ToString() {
        StringBuilder str = new StringBuilder();
        str.append("Conta ID: ").append(id).append('\n');       
        return str.toString();
    }
}
