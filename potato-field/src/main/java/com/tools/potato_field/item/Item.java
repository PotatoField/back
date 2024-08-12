package com.tools.potato_field.item;

import com.tools.potato_field.entity.Member;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private String itemURL;
    private Integer iconNumber;
    private Long id2;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Getters and Setters
}