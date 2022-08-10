package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Livro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
    
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    List<Livro> findByNomeContainingIgnoreCase(String nome);
}
