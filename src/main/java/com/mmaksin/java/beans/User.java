package com.mmaksin.java.beans;

public class User {
    private final int id;
    private final String guid;
    private final String username;

    public User(int id, String guid, String username) {
        this.id = id;
        this.guid = guid;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
