package com.tools.potato_field.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfileDto {
    private String name;
    private String description;
    private String profileImageUrl;
    private Integer height;
    private String gender;
    private Integer age;
    private boolean isProfilePublic;
    private String statusMessage;
}