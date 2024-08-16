package com.tools.potato_field.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreatePost {
    private String title;
    private String content;
    private String author;
    private String gender;
    private String style;
}
