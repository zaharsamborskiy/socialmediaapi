package com.samborskiy.socialmediaapi.api.controllers;

import com.samborskiy.socialmediaapi.api.dto.PostDTO;
import com.samborskiy.socialmediaapi.api.dto.UserDTO;
import com.samborskiy.socialmediaapi.api.service.InviteService;
import com.samborskiy.socialmediaapi.api.service.PostService;
import com.samborskiy.socialmediaapi.api.service.UserService;
import com.samborskiy.socialmediaapi.store.entities.Post;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class MainController {

    PostService postService;
    UserService userService;
    InviteService inviteService;

    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "unsecured Data";
    }
    @GetMapping("/admin")
    public String adminData(){
        return "admin Data";
    }

//  All Users
    @GetMapping("/secured")
    public ResponseEntity<?> securedData(){
        return userService.getAllUsers();
    }

    @GetMapping("/secured/{username}")
    public ResponseEntity<?> showUser(@PathVariable("username") String username) {
        return userService.showUser(username);
    }

//  Post
    @GetMapping("/info")
    public ResponseEntity<?> userData(Principal principal){
        return postService.getAllPosts(principal.getName());
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id){
        return postService.getPost(id);
    }

    @PatchMapping(value = "/info/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody Post post, Principal username) {
        return postService.updatePost(id, post, username.getName());
    }

    @PostMapping("/info")
    public ResponseEntity<?> createPost(@RequestParam(name = "title") String title,
                                        @RequestParam(name = "text") String text,
                                        @RequestParam(value = "image", required = false) MultipartFile file,
                                        Principal username) throws IOException {
        return postService.createNewPost(title, text, file, username.getName());
    }
    @DeleteMapping("/info/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id, Principal user) {
        return postService.deletePost(id, user.getName());
    }
    //friends
    @GetMapping("/friends")
    public ResponseEntity<?> showFriends(){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/friends")
    public ResponseEntity<?> inviteFriend(Principal username, @RequestParam("friend") String friendUsername){
        return inviteService.addFriend(username.getName(),friendUsername);
    }

}
