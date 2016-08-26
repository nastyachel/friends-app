package com.cheliadina.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author nastya
 */
@Entity
@Table(name = "POST")
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_time")
    private Date timeCreated;

    public Post(){
        timeCreated = new Date();
    }

    public Post(String content){
        this();
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return timeCreated != null ? timeCreated.equals(post.timeCreated) : post.timeCreated == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Post o) {
        return timeCreated.compareTo(timeCreated);
    }
}
