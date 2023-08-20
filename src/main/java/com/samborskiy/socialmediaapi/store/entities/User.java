package com.samborskiy.socialmediaapi.store.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String username;
    String password;
    String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Collection<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    Set<User> friends = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "invite_id")
    Invite invite;

    public void addPost(Post post){
        posts.add(post);
    }
    public void removePost(Post post) {
        posts.remove(post);
    }
}
