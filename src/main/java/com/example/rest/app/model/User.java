package com.example.rest.app.model;

import com.example.rest.app.enums.UserType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @OneToMany( cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "departmet_id")
    private Department department;


    public User() {
    }

    public User(Long id, String name, UserType userType, List<Ticket> tickets,Department department) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.tickets = tickets;
        this.department = department;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Ticket> getListTicket() {
        return tickets;
    }

    public void setListTicket(List<Ticket> listTicket) {
        this.tickets = listTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                userType == user.userType &&
                Objects.equals(tickets, user.tickets) &&
                Objects.equals(department, user.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userType, tickets, department);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                ", tickets=" + tickets +
                ", department=" + department +
                '}';
    }
}