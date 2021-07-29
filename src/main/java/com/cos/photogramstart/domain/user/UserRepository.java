package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

//@Repository 어노테이션 없어도 IoC에 자동으로 등록 ( 상속받으면)
public interface UserRepository extends JpaRepository<User, Long> {
    // JPA Query Method
    User findByUsername(String username);


}
