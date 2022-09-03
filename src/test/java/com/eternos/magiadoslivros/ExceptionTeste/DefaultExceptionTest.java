package com.eternos.magiadoslivros.ExceptionTeste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eternos.magiadoslivros.domain.exception.DefaultException;
import com.eternos.magiadoslivros.domain.exception.ErrorResponse;
import com.eternos.magiadoslivros.domain.exception.GlobalExceptionHandler;

@SpringBootTest
public class DefaultExceptionTest {
    
    @InjectMocks
    DefaultException defaultException;
    
    @Mock
    ErrorResponse errorResponse;
    
    @Mock
    GlobalExceptionHandler globalExceptionHandler;
    
    @Test
    void TestarDefaultException(){
    
        var mock = DefaultExceptionMock();
        
        assertNotNull(mock);
        assertNotNull(mock.getBody());
        assertEquals(HttpStatus.NOT_FOUND,mock.getStatusCode());
        assertEquals(ResponseEntity.class, mock.getClass());
        assertEquals(HttpStatus.NOT_FOUND, mock.getBody().getHttpStatus());
        assertEquals(404, mock.getBody().getCode());
        assertEquals("teste", mock.getBody().getMessage());
    
    }
    
    private ResponseEntity<ErrorResponse> DefaultExceptionMock() {
    
        DefaultException exception = new DefaultException(HttpStatus.NOT_FOUND, "teste");
    
        return new ResponseEntity<ErrorResponse>(exception.getErrorResponse(), exception.httpStatus);
        
    }
    
}
