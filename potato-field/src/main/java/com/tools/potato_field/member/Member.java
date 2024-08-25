package com.tools.potato_field.member;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "members")
@Getter@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String userID;

    private String number;


    @Column(nullable = false)
    private String name;
    private Integer height;
    private String gender;
    private Integer age;
    private String profileImageUrl;
    private String statusMessage;
    private String description;
    private boolean isProfilePublic;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // 추가적인 필드들 (예: 생년월일, 전화번호 등)
}