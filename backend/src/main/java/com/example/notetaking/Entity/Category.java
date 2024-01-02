package com.example.notetaking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notetaking_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = false)
    private String title;

    @ManyToMany(mappedBy = "categories")
    private Set<Note> notes;

    public Category(Integer id, String title, Set<Note> notes) {
        this.id = id;
        this.title = title;
        this.notes = notes;
    }
}
