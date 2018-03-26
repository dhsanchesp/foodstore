package com.foodstore.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.ISanduicheDAO;
import com.foodstore.app.entity.SanduicheEntity;

@Transactional
@Repository
public class SanduicheDAOImpl implements ISanduicheDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<SanduicheEntity> buscarTodosSanduiches() {
        final String hql = "SELECT sanduiche FROM SanduicheEntity as sanduiche"
                        + " ORDER BY sanduiche.codigoSanduiche";

        final List<SanduicheEntity> sanduiches = this.entityManager.createQuery(hql).getResultList();

        return sanduiches;

    }

    @Override
    public SanduicheEntity buscarSanduichePorCodigo(final Integer codigoSanduiche) {
        final String hql = "SELECT sanduiche FROM SanduicheEntity as sanduiche"
                        + " WHERE sanduiche.codigoSanduiche = :codigoSanduiche";

        final SanduicheEntity sanduiche = (SanduicheEntity) this.entityManager.createQuery(hql)
                        .setParameter("codigoSanduiche", codigoSanduiche)
                        .getSingleResult();

        return sanduiche;
    }

}
