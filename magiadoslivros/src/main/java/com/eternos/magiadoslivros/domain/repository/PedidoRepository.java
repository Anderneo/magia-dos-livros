package com.eternos.magiadoslivros.domain.repository;

import com.eternos.magiadoslivros.domain.model.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
