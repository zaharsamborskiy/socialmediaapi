package com.samborskiy.socialmediaapi.api.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
