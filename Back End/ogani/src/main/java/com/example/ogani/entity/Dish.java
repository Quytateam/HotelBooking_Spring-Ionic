package com.example.ogani.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;

    private String name;

    private long price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String time;

    private float distance;

    private int quantity;

    @Column(name = "description",columnDefinition = "TEXT")
    @Lob
    private String description;

}
