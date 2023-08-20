package com.samborskiy.socialmediaapi.store.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invite")
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_from")
    User from;

    @ManyToOne
    @JoinColumn(name = "user_to")
    User to;

    @Enumerated(EnumType.STRING)
    InviteStatus inviteStatus;
}
