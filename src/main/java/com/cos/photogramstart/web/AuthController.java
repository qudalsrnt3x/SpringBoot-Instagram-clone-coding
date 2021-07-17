package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String signup(SignupRequestDto signupRequestDto) { // key=value (x-www-form-urlencoded)

        log.info(String.valueOf(signupRequestDto));

        // User <- SignupRequestDto
        User user = signupRequestDto.toEntity();
        log.info(user.toString());

        User userEntity = authService.회원가입(user);
        System.out.println("userEntity = " + userEntity);

        return "/auth/signin";
    }
}
