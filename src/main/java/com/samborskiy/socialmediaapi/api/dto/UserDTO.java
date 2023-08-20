package com.samborskiy.socialmediaapi.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.samborskiy.socialmediaapi.store.entities.Post;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class UserDTO {
    Long id;
    String username;
    String email;
    List<Post> posts;

    public UserDTO(String username, List<Post> posts) {
        this.username = username;
        this.posts = posts;
    }

    public UserDTO(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
