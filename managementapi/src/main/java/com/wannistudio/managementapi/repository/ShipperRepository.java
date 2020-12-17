package com.wannistudio.managementapi.repository;

import com.wannistudio.managementapi.domain.Shipper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShipperRepository {
    private final EntityManager em;

    public void save(Shipper shipper) {
        if(shipper.getId() == null) {
            em.persist(shipper);
        }else {
            em.merge(shipper);
        }
    }

    public Shipper findOne(Long id) {
        return em.find(Shipper.class, id);
    }

    public List<Shipper> findAll() {
        return em.createQuery("select s from Shipper s", Shipper.class)
                .getResultList();
    }
}
