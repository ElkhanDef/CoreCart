package com.onlinestore.corecart.model;

import com.onlinestore.corecart.enums.RoleNames;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 250,updatable = false)
    @Enumerated(EnumType.STRING)
    private RoleNames roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(RoleNames roleName) {
        this.roleName = roleName;
    }

    public Role() {}

    public Long getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public RoleNames getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleNames roleName) {
        this.roleName = roleName;
    }
}
