package com.example.springBootFirst.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@NoArgsConstructor // Default constructor for MongoDB
@RequiredArgsConstructor // Constructor for @NonNull fields
@Setter
@Getter
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef
    private List<Journal> journalEntries = new ArrayList<>();

    private List<String> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", journalEntries=" + journalEntries +
                ", roles=" + roles +
                '}';
    }



    public User(ObjectId id, @NonNull String userName, @NonNull String password, List<Journal> journalEntries, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.journalEntries = journalEntries;
        this.roles = roles;
    }



    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    // Getters and Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("UserName cannot be null or empty");
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public List<Journal> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<Journal> journalEntries) {
        this.journalEntries = journalEntries;
    }

}
