package com.ustc.zuul;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> zuulExceptionHandler(Exception e){
        return ResponseEntity.status(404).body(new ErrorResponse("网址不正确","请您输入正确的网址！"));
    }
}
