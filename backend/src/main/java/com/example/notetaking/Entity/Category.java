package com.example.notetaking.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = false)
    private String title;
    @Column(columnDefinition = "boolean default false", nullable = true)
    private Boolean deleted;
    @ManyToMany(mappedBy = "categories")
    private Set<Note> notes;

    public Category(Integer id, String title, Boolean deleted, Set<Note> notes) {
        this.id = id;
        this.title = title;
        this.deleted = deleted;
        this.notes = notes;
    }
}
