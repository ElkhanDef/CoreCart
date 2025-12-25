package com.onlinestore.corecart.repository.impl;

import com.onlinestore.corecart.enums.RoleNames;
import com.onlinestore.corecart.model.Role;
import com.onlinestore.corecart.repository.IRoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepository implements IRoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Role> getRoleByName(RoleNames roleName) {

        return  em.createQuery("select r from Role r where r.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getResultList().stream().findFirst();


    }
}
