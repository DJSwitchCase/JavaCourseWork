package ru.mirea.coursework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "levels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Level {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "levelsIdSeq", sequenceName = "levels_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "levelsIdSeq")
    public Integer id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    public Game game_id;

    @Column(name = "complexity")
    public int complexity;

//    public Level(Integer id, String name, Game game_id, int complexity) {
//        this.id = id;
//        this.name = name;
//        this.game_id = game_id;
//        this.complexity = complexity;
//    }

    public Level() {

    }

    public String getName() {
        return name;
    }

    public void setName(String levelName) {
        this.name = levelName;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
