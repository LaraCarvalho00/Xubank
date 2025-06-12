package com.xubank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.Entity.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
}
