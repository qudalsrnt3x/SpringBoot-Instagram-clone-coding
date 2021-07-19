package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 데이터를 응답
@ControllerAdvice // 모든 익셉션을 다 낚아챈다.
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // RuntimeExcpetion이 발동하는 모든 Exception을 낚아챈다.
    public String validationException(CustomValidationException e) {
        // 1. CMRespDto, Script 비교 - 클라이언트가 응답
        // 2. Ajax 통신 - CMRespDto - 개발자
        // 3. Android 통신 - CMRespDto - 개발자가 응답

        return Script.back(e.getErrorMap().toString());
    }
}
