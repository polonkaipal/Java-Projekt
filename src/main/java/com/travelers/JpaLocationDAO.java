/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travelers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author veres
 */
public class JpaLocationDAO implements LocationDAO {
    
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
    final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void saveLocation(Location loc) {
        entityManager.getTransaction().begin();
        entityManager.persist(loc);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteLocation(Location loc) {
        entityManager.getTransaction().begin();
        entityManager.remove(loc);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateLocation(Location loc) {
        entityManager.getTransaction().begin();
        entityManager.persist(loc);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Location> getLocations() {
        TypedQuery<Location> query = entityManager.createQuery("SELECT loc FROM Location loc", Location.class);
        List<Location> locations = query.getResultList();
        return locations;
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
