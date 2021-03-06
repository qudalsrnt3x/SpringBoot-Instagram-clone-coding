package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller //Ioc , 파일을 리턴
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "/auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "/auth/signup";
    }

    // 회원가입 버튼 -> /auth/signup -> /auth/signin
    // 그러나 403 오류남 -> 시큐리티의 CSRF 토큰이 input 태그에 붙게 된다.
    // 정상적인 사용자인지, 정상적인 접근이 아닌지 구분할 때 CSRF 토큰이 사용된다.
    // 하지만 활성화시켜놓으면 자바스크립트에서도 구성하기 힘들어짐
    // 비활성화 시켜놓자.
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)

        // Valid에서 생긴 에러들을 BindingResult에 다 모아준다.
        // bindingResult에 에러가 담겨 있다면 for 문을 돌린다.

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("=====================");
                System.out.println(error.getDefaultMessage());
                System.out.println("=====================");
            }
            // 오류가 나면 Exception을 발동시킨다.
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
            // 익셉션 난 것을 핸들러를 통해 컨트롤한다.
            // hashMap 에 담은 메시지를 넘겨주자.
        } else {
            log.info(String.valueOf(signupRequestDto));

            // User <- SignupRequestDto
            User user = signupRequestDto.toEntity();
            log.info(user.toString());

            User userEntity = authService.회원가입(user);
            System.out.println("userEntity = " + userEntity);

            return "/auth/signin";
        }
    }
}
