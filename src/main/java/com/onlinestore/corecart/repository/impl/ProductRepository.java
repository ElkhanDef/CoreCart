package com.onlinestore.corecart.repository.impl;

import com.onlinestore.corecart.model.Product;
import com.onlinestore.corecart.repository.IProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements IProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Product> getBySlug(String slug) {

        return em.createQuery("select p from Product p where p.slug = :slug", Product.class)
                .setParameter("slug", slug)
                .getResultList().stream().findFirst();

    }

    @Override
    public Optional<Product> getById(Long id) {


        Product product = em.find(Product.class, id);
        return Optional.ofNullable(product);

    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }


    @Override
    public Product save(Product product) {

        em.persist(product);
        return product;
    }

    @Override
    public void delete(Product product) {

        em.remove(product);

    }

    @Override
    public boolean ifSlugExists(String slug) {


        Query query = em.createNativeQuery("select exists(select 1 from product p where p.slug = ?1)")
                .setParameter(1, slug);

        Object result = query.getSingleResult();

        return (boolean) result;


    }


}
