package com.example.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String skill;
    private String email;

    @ManyToMany(mappedBy = "job")
    private Set<User> user = new LinkedHashSet<>();
}
