package com.eternos.magiadoslivros.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eternos.magiadoslivros.domain.model.PedidoLivro;
import com.eternos.magiadoslivros.domain.model.PedidoLivroId;

public interface PedidoLivroRepository extends JpaRepository<PedidoLivro, PedidoLivroId>{

}
