package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{
    
}
