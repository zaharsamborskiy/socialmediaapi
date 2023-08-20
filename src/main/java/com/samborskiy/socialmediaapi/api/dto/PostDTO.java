package com.samborskiy.socialmediaapi.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PostDTO {
    String title;
    String text;
    String imageName;
}
