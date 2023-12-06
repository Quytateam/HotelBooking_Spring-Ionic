package com.example.ogani.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String address;

    private float star;

    private long review;

    private String image;

    @JoinColumn(name = "working_time")
    private String workingTime;

    private long price;

    private float distance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "chef_dishs",joinColumns = @JoinColumn(name = "chef_id"),inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishs = new HashSet<>();
}
