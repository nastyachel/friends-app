package com.cheliadina.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author nastya
 */
@Entity
@Table(name = "MESSAGE")
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private int id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "TIME_SENT")
    private Date timeSent;

    @ManyToOne
    @JoinColumn(name = "USER_FROM_ID")
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "USER_TO_ID")
    private User userTo;

    public Message(){
        timeSent = new Date();
    }

    public Message(String content, User userFrom, User userTo){
        this();
        this.content = content;
        this.userFrom = userFrom;
        this.userTo = userTo;
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

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Date getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }

    @Override
    public int compareTo(Message otherMessage) {
        return timeSent.compareTo(otherMessage.getTimeSent());
    }
}