package ru.mirea.coursework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "games")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String name;
    @Column(name = "date")
    public String creationDate;

    public Game(String name, String creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public Game(Integer id, String name, String creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Game() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
