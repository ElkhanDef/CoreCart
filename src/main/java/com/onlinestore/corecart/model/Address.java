package com.onlinestore.corecart.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "district", length = 100, nullable = false)
    private String district;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToOne(mappedBy = "address")
    private User users;


    public Address(String address, String district, String city) {
        this.address = address;
        this.district = district;
        this.city = city;
    }

    public Address() {

    }

    @PrePersist
    public void prePersist() {

        this.lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = LocalDateTime.now();
    }


}
