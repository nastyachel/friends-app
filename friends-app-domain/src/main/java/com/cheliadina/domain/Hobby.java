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

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @Column(name = "users")
    List<User> users;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        if(!users.contains(user)){
            users.add(user);
        }
    }

}
