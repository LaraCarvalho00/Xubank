package com.xubank.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.xubank.Entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
