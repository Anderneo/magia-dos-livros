package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.request.LivroRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroService {
    
    private final FornecedorService fornecedorService;
    private final LivroRepository livroRepository;

    public Livro salvar(LivroRequest livroRequest){
        Livro livro = Livro.builder()
        .tagEstoque(livroRequest.getTagEstoque())
        .nome(livroRequest.getNome())
        .descricao(livroRequest.getDescricao())
        .isbn(livroRequest.getIsbn())
        .quantLivros(livroRequest.getQuantLivros())
        .valorRecebimento(livroRequest.getValorRecebimento())
        .valorVenda(livroRequest.getValorVenda())
        .fornecedor(fornecedorService.buscarPorIdOuFalhar(livroRequest.getId_fornecedor()))
        .build();

        return livroRepository.save(livro);

    }

    public List<Livro> buscarTodos(){
        return livroRepository.findAll();
    }
}
