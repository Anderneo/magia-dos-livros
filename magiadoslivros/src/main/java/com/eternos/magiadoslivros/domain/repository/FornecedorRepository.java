package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Fornecedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{
    Optional<Fornecedor> findByRazaoSocial(String razaoSocial);
    Optional<Fornecedor> findByCnpj(String cnpj);
}
