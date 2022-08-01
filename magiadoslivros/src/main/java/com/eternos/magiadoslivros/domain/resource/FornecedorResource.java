package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.service.FornecedorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "fornecedor")
@AllArgsConstructor
public class FornecedorResource {

    private final FornecedorService fornecedorService;
    @GetMapping(path = "todos")
    public List<Fornecedor>  buscar() {
        return fornecedorService.buscarTodos();
    }
    
}
