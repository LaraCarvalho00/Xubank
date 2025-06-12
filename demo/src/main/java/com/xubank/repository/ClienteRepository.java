package com.xubank.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
