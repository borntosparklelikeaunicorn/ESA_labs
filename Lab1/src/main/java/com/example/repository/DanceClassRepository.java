package com.example.repository;

import com.example.models.DanceClass;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Stateless
@RequiredArgsConstructor
public class DanceClassRepository {
    @PersistenceContext
    private EntityManager em;

    public List<DanceClass> getAll() {
        return em.createQuery(
                        "SELECT p FROM danceclass p",
                        DanceClass.class)
                .getResultList();
    }

    public void  save(DanceClass newClass){
        em.persist(newClass);
        em.flush();
    }
}
