package com.foodstore.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.foodstore.app.dao.IIngredienteDAO;
import com.foodstore.app.entity.IngredienteEntity;

@Transactional
@Repository
public class IngredienteDAOImpl implements IIngredienteDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngredienteDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<IngredienteEntity> buscarTodosIngredientes() {

        final String hql = "SELECT ingrediente FROM IngredienteEntity as ingrediente ORDER BY ingrediente.codigoIngrediente";

        return this.entityManager.createQuery(hql).getResultList();
    }

    @Override
    public IngredienteEntity buscarIngredientePorCodigo(final Integer codigoIngrediente) {

        final String hql = "SELECT ingrediente FROM IngredienteEntity as ingrediente WHERE ingrediente.codigoIngrediente = :codigoIngrediente";

        return (IngredienteEntity) this.entityManager.createQuery(hql)
                        .setParameter("codigoIngrediente", codigoIngrediente).getSingleResult();
    }

    @Override
    public IngredienteEntity atualizarIngrediente(final IngredienteEntity ingredienteEntity) {

        try {
            return this.entityManager.merge(ingredienteEntity);

        } catch (Exception e) {
            LOGGER.error("Erro ao tualizar ingrediente: ", e);
            throw new DataAccessResourceFailureException("Erro ao tualizar ingrediente");
        }
    }

}
