package com.cheliadina.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author nastya
 */
@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private int id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "TIME_SENT")
    private Date timeSent;

    @ManyToOne
    @JoinColumn(name = "PERSON_FROM")
    private User personFrom;

    @ManyToOne
    @JoinColumn(name = "PERSON_TO")
    private User personTo;

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

    public User getPersonFrom() {
        return personFrom;
    }

    public void setPersonFrom(User personFrom) {
        this.personFrom = personFrom;
    }

    public User getPersonTo() {
        return personTo;
    }

    public void setPersonTo(User personTo) {
        this.personTo = personTo;
    }
}