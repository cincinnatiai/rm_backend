package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "characters")
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;

    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "origin_id")
    private Location origin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "image_url", nullable = false)
    private String image;
}
