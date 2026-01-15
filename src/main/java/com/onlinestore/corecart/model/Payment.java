package com.onlinestore.corecart.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany
    private List<Product> product;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
