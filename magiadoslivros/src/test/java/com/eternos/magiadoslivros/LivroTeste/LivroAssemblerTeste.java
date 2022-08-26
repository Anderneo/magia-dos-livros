package com.eternos.magiadoslivros.LivroTeste;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    @Mock
    private LivroRequest livroRequest;

    @Mock
    private Livro livro;

    @Mock
    private ModelMapper modelMapper;

    // @Test
    // void testarToModel(){
    //     var objFornecedor = fornecedorMock();
    //     TypeMap<Object, Object> objTypeMap = typeMapMock();
    //     var objLivroRequest = livroRequestMock();


    //     when(fornecedorUtil.buscarFornecedor(any())).thenReturn(objFornecedor);

    //     when(modelMapper.getTypeMap(any(), any())).thenReturn(objTypeMap);
        
        

    //     // when(modelMapper.map(any(), any())).thenReturn(livro);
        
    //     var mock = livroAssembler.toModel(objLivroRequest);
      
    //     assertNotNull(mock);

        

    // }

    private TypeMap<LivroRequest, Livro> typeMapMock(){
        TypeMap<LivroRequest, Livro> typeMap = modelMapper.getTypeMap(LivroRequest.class, 
       Livro.class);
            
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
    
}
