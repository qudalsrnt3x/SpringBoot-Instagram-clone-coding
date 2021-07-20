package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // 권한을 가져오는 함수
        // 권한은 User의 Role이 가지고 있음
        //return user.getRole(); 타입이 Collection으로 들어옴
        // 권한이라는 건 한개 이상일 수 있기 때문

        Collection<GrantedAuthority> collector = new ArrayList<>(); // ArrayList의 부모가 Collection

        /*collector.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
            // 너무 지저분함 -> 람다식으로 변환가능*/

        collector.add(() -> user.getRole());

        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // false로 되어있으면 로그인이 안된다.
    // 나중에 회사 같은 곳 가면 쓸 수 있음
}
