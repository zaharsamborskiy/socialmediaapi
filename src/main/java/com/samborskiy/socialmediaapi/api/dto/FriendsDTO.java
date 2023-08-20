package com.samborskiy.socialmediaapi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendsDTO {
    String userFrom;
    String userTo;
}
