package com.onlinestore.corecart.repository.impl;

import com.onlinestore.corecart.model.User;
import com.onlinestore.corecart.repository.IUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Optional<User> getUserByEmail(String email) {

        try {
            User user = em.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return Optional.ofNullable(user);

        } catch (NoResultException noResultException) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void delete(User user) {

        em.remove(user);

    }
}
