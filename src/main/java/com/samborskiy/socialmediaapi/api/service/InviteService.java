package com.samborskiy.socialmediaapi.api.service;

import com.samborskiy.socialmediaapi.api.dto.FriendsDTO;
import com.samborskiy.socialmediaapi.store.entities.User;
import com.samborskiy.socialmediaapi.store.repositories.InviteRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InviteService {
    UserService userService;
    InviteRepository inviteRepository;

    public ResponseEntity<?> addFriend(String user, String friend){
        Optional<User> userFrom = userService.findByUserName(user);
        Optional<User> userTo = userService.findByUserName(friend);
        return ResponseEntity.ok(new FriendsDTO(user, friend));
    }
}
