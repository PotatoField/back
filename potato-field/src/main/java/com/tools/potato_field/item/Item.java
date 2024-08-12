package com.tools.potato_field.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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