package com.example.repository;


import com.example.models.DanceClass;
import com.example.models.DanceStudio;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Stateless
@RequiredArgsConstructor
public class DanceStudioRepository {
    @PersistenceContext
    private EntityManager em;
    public List<DanceStudio> getAll() {
        return em.createQuery(
                        "SELECT p FROM dancestudio p",
                        DanceStudio.class)
                .getResultList();
    }

    public void  save(DanceStudio newStudio){
        em.persist(newStudio);
        em.flush();
    }
}
