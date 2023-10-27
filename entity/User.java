package uz.pdp.online.lesson10.task2.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {
    private long id;
    private String name;
    private String email;
    private LocalDateTime registerTime;

    public User() {
    }

    public User(long id, String name, String email, LocalDateTime registerTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registerTime = registerTime;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registerTime=" + registerTime +
                '}';
    }
}
