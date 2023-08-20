package com.samborskiy.socialmediaapi.store.repositories;

import com.samborskiy.socialmediaapi.store.entities.Post;
import com.samborskiy.socialmediaapi.store.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    void deleteById(Long id);

    Optional<Post> findById(Long id);
}
