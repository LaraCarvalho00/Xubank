package com.xubank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.Entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
