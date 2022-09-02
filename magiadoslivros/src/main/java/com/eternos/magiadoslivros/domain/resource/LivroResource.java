package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public Livro salvar(@Validated @RequestBody LivroRequest livroRequest){
  
        return livroService.salvar(livroRequest);
    }


    @GetMapping(path = "todos")
    public List<Livro> buscar() {
        return livroService.buscarTodos();
    }

    @GetMapping(path = "buscar/nome")
    public List<Livro> buscarNome(@Valid @RequestParam String nome ){
        return livroService.buscarNome(nome);
    }
    
    @GetMapping(path = "buscar/isbn")
    public Livro buscarIsbn(@Valid @RequestParam String isbn){
            return livroService.buscarIsbn(isbn);
    }


    @DeleteMapping(path = "deletar/{id}")
    public void deletar(@Valid @PathVariable("id") Integer id){
        livroService.deletar(id);
    }

    @PatchMapping(path = "adicionarestoque/{id}")
    public Livro atualizarEstoque(@Validated @PathVariable("id") Integer id, @RequestBody Integer quantLivros ){
        return livroService.atualizarQtdeLivro(id, quantLivros);
    }

    @PutMapping(path = "atualizar/{id}")
    public Livro update(@Validated @PathVariable("id") Integer id, @RequestBody LivroRequest livroRequest){
        return livroService.atualizarLivro(id, livroRequest);
    }

}


