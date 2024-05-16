package com.nd.tepia.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sessions")
public class Session implements Serializable{
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionKey;
    private Boolean active;

    private LocalDateTime expires;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Session(){

    }

    public Session(Long id, String sessionKey, User user) {
        this.id = id;
        this.sessionKey = sessionKey;
        this.user = user;
    }

    public Session(Long id, String sessionKey, User user, LocalDateTime expires) {
        this.id = id;
        this.sessionKey = sessionKey;
        this.user = user;
        this.expires = expires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String key) {
        this.sessionKey = key;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public static String generateKey(){
        StringBuilder builder = new StringBuilder();
        String from = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                builder.append(from.charAt((int)Math.round(Math.random() * 61)));
            }
            if(i!=4) builder.append("-");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (sessionKey == null) {
            if (other.sessionKey != null)
                return false;
        } else if (!sessionKey.equals(other.sessionKey))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    
}
