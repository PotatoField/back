package com.tools.potato_field.entity;

@Entity
public class Category_1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genderName;
}
