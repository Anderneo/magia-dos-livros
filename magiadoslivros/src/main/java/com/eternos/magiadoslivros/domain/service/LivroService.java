package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.request.LivroRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Service
@Builder
public class LivroService {
    
    private final LivroRepository livroRepository;
    

    public Livro salvar(LivroRequest livroRequest){

        return livroRepository.save(livroRequest.converterClasse());

    }

    public List<Livro> buscarTodos(){
        return livroRepository.findAll();
    }
}
