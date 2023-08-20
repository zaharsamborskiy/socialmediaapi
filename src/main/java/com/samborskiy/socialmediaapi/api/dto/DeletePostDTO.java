package com.samborskiy.socialmediaapi.api.dto;

import com.samborskiy.socialmediaapi.store.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class DeletePostDTO {
    String message;
    Post post;
}
