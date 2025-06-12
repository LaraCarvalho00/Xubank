package com.xubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.model.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
}
