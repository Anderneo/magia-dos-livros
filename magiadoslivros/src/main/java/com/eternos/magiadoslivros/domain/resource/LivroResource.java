package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.service.LivroService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "livro")
@AllArgsConstructor
public class LivroResource {
    
    private final LivroService livroService;

    @PostMapping
    public Livro salvar(@RequestBody @Valid LivroRequest livroRequest){
        return livroService.salvar(livroRequest);
    }


    @GetMapping(path = "todos")
    public List<Livro> buscar() {
        return livroService.buscarTodos();
    }

    @GetMapping(path = "buscar/nome")
    public List<Livro> buscarNome(@RequestParam String nome ){
        return livroService.buscarNome(nome);
    }

    @DeleteMapping(path = "{id}")
    public void deletar(@PathVariable Integer id){
        livroService.deletar(id);
    }

}


