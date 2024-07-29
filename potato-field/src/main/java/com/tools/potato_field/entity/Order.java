package com.example.ex01.service;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;
}