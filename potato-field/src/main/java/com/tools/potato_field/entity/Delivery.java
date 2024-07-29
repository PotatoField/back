package com.tools.potato_field.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String status;
}