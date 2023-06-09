package com.example.springbootserver.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.example.springbootserver.core.dto.ResponseDTO;

// 권한 없음
@Getter
public class Exception403 extends RuntimeException{
    public Exception403(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.fail(403, "forbidden", getMessage());
        return responseDTO;
    }

    public HttpStatus status(){
        return HttpStatus.FORBIDDEN;
    }
}
