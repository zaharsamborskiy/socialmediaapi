package com.samborskiy.socialmediaapi.store.repositories;

import com.samborskiy.socialmediaapi.store.entities.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
    void deleteById(Long id);
    Optional<Invite> findById(Long id);

}
