package com.cheliadina.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @author nastya
 */

@Entity
@Table(name="HOBBY")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    public Hobby(){

    }

    public Hobby(String title){
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
