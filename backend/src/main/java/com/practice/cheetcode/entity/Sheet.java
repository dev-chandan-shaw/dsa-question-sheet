package com.practice.cheetcode.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne
    @JsonIgnore
    private User createdBy;

    private String slug;

    public Sheet() {}

    public Sheet(String title, User createdBy) {
        this.title = title;
        this.createdBy = createdBy;
        this.slug = toSlug(title + "-" + UUID.randomUUID().toString().substring(0, 8));
    }



    private String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9]", "-")
                .replaceAll("-{2,}", "-")
                .replaceAll("^-|-$", "");
    }
}
