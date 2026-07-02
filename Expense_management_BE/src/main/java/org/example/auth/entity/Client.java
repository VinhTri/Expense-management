package org.example.auth.entity;

import jakarta.persistence.*;

@Entity
@Table (name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable=false, length=50)
    private String username;
    @Column( nullable = false, length=100)
    private String email;
    @Column(nullable=false, length=100)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20, updatable=false)
    private Role role;
    @Column(nullable = false)
    private boolean isActive;
protected Client(){}
    public Client(String username, String email, String password, Role role) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email không được để trống");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống");
        }
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = true;
    }
    public int getId() {
    return id;
    }
    public String getUsername() {
    return username;
    }
    public String getEmail() {
    return email;
    }
    public String getPassword() {
    return password;
    }
    public Role getRole() {
    return role;
    }
    public boolean isActive() {
    return isActive;
    }
}
