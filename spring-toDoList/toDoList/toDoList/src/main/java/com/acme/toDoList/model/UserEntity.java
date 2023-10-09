package com.acme.toDoList.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="login", length=10, nullable=false, unique=true)
    private String username;
    @Column(name="secret", length=8, nullable=false, unique=true)
    private String password;
    @Column(name="displayName", length=20, nullable=false)
    private String name;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}
