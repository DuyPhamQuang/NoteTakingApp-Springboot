package com.example.notetaking.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notetaking_note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String content;
    @CreationTimestamp
    private LocalDateTime createdtime;
    @UpdateTimestamp
    private LocalDateTime updatedtime;
    @Column(columnDefinition = "boolean default false", nullable = true)
    private Boolean deleted;

    @ManyToMany
    @Column(nullable = true)
    @JoinTable(
        name = "notetaking_categorynote",
        joinColumns = @JoinColumn(name = "note_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Note(Integer id, String title, String content, Boolean deleted, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
        this.categories = categories;
    }

    @PrePersist
    public void prePersist() {
        deleted = false;
    }

}
