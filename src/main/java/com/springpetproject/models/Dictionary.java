package com.springpetproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dictionaries")
public class Dictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "[\\w]?")
    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotBlank
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "dictionary")
    private List<WordTranslate> wordTranslates;

    public Dictionary() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<WordTranslate> getWordTranslates() {
        return wordTranslates;
    }

    public void setWordTranslates(List<WordTranslate> wordTranslates) {
        this.wordTranslates = wordTranslates;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", owner=" + owner +
                '}';
    }
}
