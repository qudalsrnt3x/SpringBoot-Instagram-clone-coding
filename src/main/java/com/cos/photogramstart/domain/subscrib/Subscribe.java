package com.cos.photogramstart.domain.subscrib;

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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"} // 실제 DB의 컬럼명을 넣어야 한다.
                )
        }
)
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private int id;

    @JoinColumn(name = "fromUserId")
    @ManyToOne
    private User fromUser; // 구독 하는 애

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser; // 구독 받는 애

    private LocalDateTime createDate;

    @PrePersist // 디비에 INSERT 되기 직전에 시행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
