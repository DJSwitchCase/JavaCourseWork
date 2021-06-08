package ru.mirea.coursework.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;


    @Column(name = "name")
    public String name;
    @Column(name = "price")
    public String price;

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Item() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
