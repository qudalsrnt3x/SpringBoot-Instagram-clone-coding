package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

// 데이터를 전송하는데 필요한 데이터를 담는데 사용한다.
// 회원가입 폼의 데이터를 담아주자.
@Data //Getter, Setter
public class SignupRequestDto {

    private String username;
    private String password;
    private String email;
    private String name;

}
