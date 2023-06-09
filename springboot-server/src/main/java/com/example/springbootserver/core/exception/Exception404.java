package com.example.springbootserver.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.example.springbootserver.core.dto.ResponseDTO;

// 리소스 없음
@Getter
public class Exception404 extends RuntimeException{
    public Exception404(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.fail(404,"notFound", getMessage());
        return responseDTO;
    }

    public HttpStatus status(){
        return HttpStatus.NOT_FOUND;
    }
}
