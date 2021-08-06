package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 유저 정보, 이미지, 구독정보를 다 가져와야 한다.
    // 유저 정보를 가져올 때, 유저가 가지고 있는 이미지를 같이 가져오는 로직을 구현해야 한다 -> 양방향 매핑
    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model,
                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);

        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) { // 세션에 접근가능
        //1. 어노테이션으로 세션에 바로접근 (추천)
        //System.out.println("세션 정보: " + principalDetails.getUser());

        //2. 직접 세션에 접근하는 방법
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) authentication.getPrincipal();
        //System.out.println("직접 찾은 세션 정보 : " + mPrincipalDetails.getUser());

        //model.addAttribute("principal", principalDetails.getUser());
        // model에 안넘겨도 된다.

        return "user/update";
    }
}
