package com.eternos.magiadoslivros.domain.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
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

    //PARAMETROS FORNECIDOS PELO QUERY STRING
    @GetMapping(path = "todos")
    public List<Livro>  buscar() {
        return livroService.buscarTodos();
    }

    // PARAMETROS FORNECIDOS PELO PATHVARIABEL
    /*@GetMapping(path = "{id:[0-9]+}")
    public Sala buscarTodos(@PathVariable Long id){
        return salaService.buscarPorIdOuFalhar(id);
    }

    @DeleteMapping(path = "{id}")
    public void deletar(@PathVariable Long id){
        salaService.deletar(id);
    }

    @PutMapping(path = "{id}")
    public String update(@PathVariable Long id, @RequestBody SalaRequest salaRequest){
        return "id igual " + id + " obj: " + salaRequest;
    }

    @PatchMapping(path = "nome/{id}")
    public String atualizarUnico(@PathVariable Long id, @RequestBody String nome ){
        return "id=" + id + " nome = " + nome;
    }*/

}


