package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name; // 필수
    private String password; // 필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 줘도 되고 안줘도 되는 애들은 Entity로 만들면 위험하다
    // 위험함, 코드수정이 필요할 예정
    public User toEntity() {
        return User.builder()
                .name(name) // 얘도 기재 안하면 문제
                .password(password) // 사용자가 패스워드 기재 안했으면 공백으로 들어온다..문제!! Validation 체크
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
