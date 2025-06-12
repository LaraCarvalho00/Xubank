package com.xubank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
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

    public abstract void sacar(double valor);
    public abstract void depositar(double valor);

    /**
     * Gera o extrato com as operações do último mês.
     * @return
     */
    public String getExtratoMensal() {
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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getSaldo() { return saldo; }
    protected void setSaldo(double saldo) { this.saldo = saldo; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Operacao> getOperacoes() { return operacoes; }
    public void setOperacoes(List<Operacao> operacoes) { this.operacoes = operacoes; }

    public void adicionarOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }
}
