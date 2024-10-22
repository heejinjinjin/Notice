package com.example.notice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Notice extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; //Foreign Key 설정(참조무결성 유지)

    // 변경된 제목으로 수정
    public void changeTitle(String title) {
        this.title = title;
    }
    //변경된 내용으로 수정
    public void changeContent(String content) {
        this.content = content;
    }
}
