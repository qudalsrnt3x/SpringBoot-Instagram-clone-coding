package com.cos.photogramstart.config.auth;


import com.cos.photogramstart.config.SecurityConfig;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // IoC
public class PrincipalDetailsService implements UserDetailsService {

    // 진행과정
    // SecurityConfig에 Post로 로그인 URL 설정
    // 브라우저가 /auth/signin 으로 들어오면
    // 바디에 username, password를 가지고 시큐리티 설정파일로 들어옴
    // 이 때 UserDetailsService가 가로챔
    // 우린 UserDetailsService를 Implements한 PrincipalDetailsService 가 있기 때문에
    // 얘를 통해서 로그인이 진행된다.

    private final UserRepository userRepository;

    // 2.리턴이 잘 되면 자동으로 UserDetails 타입으로 세션을 만든다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("나 실행돼? "+username);
        // 로그인을 진행할건데 던져준건 username
        // 1.password는 시큐리티가 알아서 확인해줌

        // DB에 username이 있는지 없는지만 확인해주면 된다.
        User userEntity = userRepository.findByUsername(username);

        if (username == null) {
            return null;
        } else {
            //return userEntity;
            // userDetails를 리턴해야한다.
            return new PrincipalDetails(userEntity);
        }
    }
}
