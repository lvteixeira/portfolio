package com.acme.toDoList.dto;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String name;

    public UserDTO() {}

    public UserDTO(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
}