package com.xubank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
