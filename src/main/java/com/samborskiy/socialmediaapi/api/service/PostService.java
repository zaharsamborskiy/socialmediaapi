package com.samborskiy.socialmediaapi.api.service;

import com.samborskiy.socialmediaapi.api.dto.PostDTO;
import com.samborskiy.socialmediaapi.store.entities.Post;
import com.samborskiy.socialmediaapi.store.entities.User;
import com.samborskiy.socialmediaapi.store.repositories.PostRepository;
import com.samborskiy.socialmediaapi.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    ImageService imageService;
    UserService userService;

    public ResponseEntity<?> createNewPost(String title, String text, MultipartFile file, String username) throws IOException {
        Optional<User> user = userService.findByUserName(username);
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        if (file!=null) {
            post.setImage(imageService.createNewImage(file));
        }
        user.ifPresent(newPost -> newPost.addPost(post));
        postRepository.saveAndFlush(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }


    public ResponseEntity<?> getAllPosts(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getPosts().stream()
                    .map(post -> new PostDTO(post.getTitle(), post.getText(), post.getImage().getName()))
            );
        }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Transactional
    public ResponseEntity<?> getPost(Long id){
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            byte[] imageData = imageService.downloadImage(post.get().getImage().getName());
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<?> deletePost(Long id, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Post> post = postRepository.findById(id);

        user.ifPresent(value -> value.getPosts().forEach(p  -> {
            if (p.getId().equals(post.get().getId())) {
                postRepository.deleteById(id);
            }
        }));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updatePost(Long id, Post post, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Post> postInRepository = postRepository.findById(id);
        postInRepository.ifPresent(p -> {
            p.setTitle(post.getTitle());
            p.setText(post.getText());
            p.setImage(post.getImage());
            user.ifPresent(u -> {
                u.removePost(postInRepository.get());
                u.addPost(p);
                postRepository.saveAndFlush(postInRepository.get());
            });
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
