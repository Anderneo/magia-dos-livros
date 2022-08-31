package com.eternos.magiadoslivros.LivroTeste;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.test.context.SpringBootTest;

import com.eternos.magiadoslivros.domain.assembler.LivroAssembler;
import com.eternos.magiadoslivros.domain.model.Fornecedor;
import com.eternos.magiadoslivros.domain.model.Livro;
import com.eternos.magiadoslivros.domain.request.LivroRequest;
import com.eternos.magiadoslivros.domain.util.FornecedorUtil;

@SpringBootTest
public class LivroAssemblerTeste {

    @InjectMocks
    private LivroAssembler livroAssembler;

    @Mock
    private FornecedorUtil fornecedorUtil;

    @Spy
    private LivroRequest livroRequest;

    @Spy
    private Livro livro;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testarToModel(){
        var objFornecedor = fornecedorMock();
        //TypeMap<LivroRequest, Livro> objTypeMap = typeMapMock();
        var objLivro = livroMock();
        var objLivroRequest = livroRequestMock();

        when(fornecedorUtil.buscarFornecedor(any())).thenReturn(objFornecedor);

        Mockito.spy(modelMapper.getTypeMap(LivroRequest.class, Livro.class));
        
        Mockito.spy(modelMapper.createTypeMap(LivroRequest.class, Livro.class));

        when(modelMapper.map(any(), any())).thenReturn(objLivro);
        
        var mock = livroAssembler.toModel(objLivroRequest);
      
        assertNotNull(mock);

        

    }

    private TypeMap<LivroRequest, Livro> typeMapMock(){
        TypeMap<LivroRequest, Livro> typeMap = 
                modelMapper.createTypeMap(LivroRequest.class, Livro.class)
                        .addMappings(mapper-> mapper.skip(Livro::setIdLivro))
                        .addMapping(LivroRequest::getIdFornecedor, Livro::setIdFornecedor);
            
       return typeMap;
    }


    @Test
    void testarToCollectionModel(){
    }

    private Fornecedor fornecedorMock(){
        Fornecedor fornecedor = new Fornecedor();
        return fornecedor;
    }



    private LivroRequest livroRequestMock() {
        LivroRequest livroRequest = new LivroRequest();
        livroRequest.setDescricao("teste");
        livroRequest.setIsbn("teste");
        livroRequest.setNome("O diário de Anne Frank");
        livroRequest.setTagEstoque("fds");
        livroRequest.setQuantLivros(2);
        livroRequest.setValorRecebimento(50.00);
        livroRequest.setValorVenda(55.00);
        return livroRequest;
    }
    
    private Livro livroMock() {
        Livro livro = new Livro();
        livro.setDescricao("teste");
        livro.setIsbn("teste");
        livro.setNome("O diário de Anne Frank");
        livro.setTagEstoque("fds");
        livro.setQuantLivros(2);
        livro.setValorRecebimento(50.00);
        livro.setValorVenda(55.00);
        return livro;
    }
}
