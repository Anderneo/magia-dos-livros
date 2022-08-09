package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByCpf(String cpf);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
