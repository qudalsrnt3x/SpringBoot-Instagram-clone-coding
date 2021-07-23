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
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
