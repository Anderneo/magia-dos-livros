package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
