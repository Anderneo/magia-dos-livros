package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.request.FornecedorRequest;
import com.eternos.magiadoslivros.domain.service.FornecedorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "fornecedor")
@AllArgsConstructor
public class FornecedorResource {

    private final FornecedorService fornecedorService;

    @PostMapping
    public Fornecedor salvar(@Validated @RequestBody FornecedorRequest fornecedorRequest){
        
        return fornecedorService.salvar(fornecedorRequest);
    }

    @GetMapping(path = "buscar/razaosocial")
    public Fornecedor buscarRazaoSocial(@RequestParam String razaosocial ){
        return fornecedorService.buscarRazaoSocial(razaosocial);
    }

    @GetMapping(path = "buscar/cnpj")
    public Fornecedor buscarCnpj(@RequestParam String cnpj ){
        return fornecedorService.buscarCnpj(cnpj);
    }

    @GetMapping(path = "todos")
    public List<Fornecedor> buscar() {
        return fornecedorService.buscarTodos();
    }

    @DeleteMapping(path = "{id}")
    public void deletar(@PathVariable Integer id){
        fornecedorService.deletar(id);
    }
}
