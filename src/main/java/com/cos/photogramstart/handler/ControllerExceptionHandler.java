package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 데이터를 응답
@ControllerAdvice // 모든 익셉션을 다 낚아챈다.
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // RuntimeExcpetion이 발동하는 모든 Exception을 낚아챈다.
    public CMRespDto<?> validationException(CustomValidationException e) {
        return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
    }
}
