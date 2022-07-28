package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.resource.LivroRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;

    public Livro salvar(LivroRequest livroRequest){

        return livroRepository.save(livroRequest.converterClasse());

    }

    public List<Livro> buscarTodos(){
        return livroRepository.findAll();
    }
}
