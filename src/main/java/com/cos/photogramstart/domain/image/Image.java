package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image { // N, 1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // 설명
    private String postImageUrl; // 사진을 전송 받아서 그 사진을 서버의 특정 폴더에 저장할 것 - DB에 그 저장된 경로를 insert

    @JoinColumn(name = "UserId")
    @ManyToOne
    private User user; // 누가 업로드했는지 알아야지
    //1, 1
    // 객체가 DB에 저장될 때는 FK로 저장이 된다. 그래서 이름 지정 가능

    // 이미지 좋아요

    // 이미지 댓글

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
