package com.samborskiy.socialmediaapi.store.entities;




import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Entity @Builder
@Setter @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor @AllArgsConstructor
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String type;

    @Lob
    byte[] imageData;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "image")
    @JsonIgnore
    Post post;
}
