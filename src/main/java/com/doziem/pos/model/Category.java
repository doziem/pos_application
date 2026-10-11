package com.doziem.pos.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String categoryId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}
