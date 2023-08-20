package com.samborskiy.socialmediaapi.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUser {
    String username;
    String password;
    String confirmPassword;
    String email;
}
