package com.tools.potato_field.like;

import com.tools.potato_field.member.Member;
import com.tools.potato_field.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Like {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)//데이터베이스 테이블에서 Member 엔티티의 id를 참조하는 외래키
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
