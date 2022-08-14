package com.eternos.magiadoslivros.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.repository.LivroRepository;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;
import com.eternos.magiadoslivros.domain.util.LivroUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroService {
    
    private final LivroRepository livroRepository;
    private final FornecedorUtil fornecedorUtil;
    private final LivroAssembler livroAssembler;
    private final LivroUtil livroUtil;


    public Livro salvar(LivroRequest livroRequest){

        livroUtil.checarIsbn(livroRequest.getIsbn());

        var livro = livroAssembler.toModel(livroRequest);

        return livroRepository.save(livro);
    }

    public List<Livro> buscarTodos(){

        return livroRepository.findAll();

    }

    public List<Livro> buscarNome(String nome){

        List<Livro> livro = livroRepository.findByNomeContainingIgnoreCase(nome);

        if(livro.isEmpty()) throw new DefaultException(HttpStatus.NOT_FOUND, 
                     "Não foi possivel encontrar nenhum registro com nome:" + nome);

        return livro;

    }

    public Livro buscarIsbn(String isbn){

        livroUtil.checarIsbn(isbn);

        Livro livro = livroRepository.findByIsbn(isbn);

        return livro;

    }

    public void deletar(Integer id){

        var objecto = livroUtil.buscarId(id);

        livroRepository.delete(objecto);

        throw new DefaultException(
                                    HttpStatus.ACCEPTED,
                                    "Registro " + id + " deletado com sucesso!!");
                                    
    }

    public Livro atualizarQtdeLivro(Integer id, Integer quantLivros){

        var livro = livroUtil.buscarId(id);

        if(livro.getQuantLivros() < 0) throw new DefaultException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "O estoque está com o valor negativo!!");

        livro.setQuantLivros(quantLivros + livro.getQuantLivros());

        return livroRepository.save(livro);

    }

    public Livro atualizarLivro(Integer id, LivroRequest livroRequest){

        var livro = livroUtil.buscarId(id);

        Fornecedor fornecedor = fornecedorUtil.buscarId(livroRequest.getIdFornecedor());
     
        BeanUtils.copyProperties(livroRequest, livro, "id", "isbn");

        livro.setIdFornecedor(fornecedor);

        return livroRepository.save(livro);

    }

}
