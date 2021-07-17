package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service //Ioc, 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // 해당 함수가 실행되고 종료되기 전까지 트랜잭션 실행
    // Write 할 때 실행, Insert, Update, Delete
    public User 회원가입(User user) {
        // 패스워드 암호화
        String rowPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rowPassword);

        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        // 회원가입 진행
        User userEntity = userRepository.save(user); // DB에 들어간 후에 응답받은 데이터
        return userEntity;
    }


}
