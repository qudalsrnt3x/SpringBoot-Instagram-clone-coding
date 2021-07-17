package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service //Ioc, 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;

    public User 회원가입(User user) {
        // 회원가입 진행
        User userEntity = userRepository.save(user); // DB에 들어간 후에 응답받은 데이터
        return userEntity;
    }


}
