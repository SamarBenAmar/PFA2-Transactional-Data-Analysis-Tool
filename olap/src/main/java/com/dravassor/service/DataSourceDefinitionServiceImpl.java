package com.dravassor.service;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.dravassor.domain.DataSourceDefinition;



@ApplicationScoped
public class DataSourceDefinitionServiceImpl implements DataSourceDefinitionService {

    @Inject
    EntityManager em;

    @Transactional
    public DataSourceDefinition create(DataSourceDefinition ds) {
        em.persist(ds);
        return ds;
    }

    public DataSourceDefinition findById(Long id) {
        return em.find(DataSourceDefinition.class, id);
    }

    @Override
    public List<DataSourceDefinition> findByLabel(String label) {
        return (List<DataSourceDefinition>) em.createQuery("SELECT datasourcedefinition FROM DataSourceDefinition datasourcedefinition where datasourcedefinition.label=:param1", DataSourceDefinition.class).setParameter("param1", label).getResultList();
    }

    @Transactional
    @Override
    public DataSourceDefinition update(DataSourceDefinition ds) {
        em.merge(ds);
        return ds;
    }

    @Transactional
    @Override
    public List<DataSourceDefinition> findAll() {
        return (List<DataSourceDefinition>) em.createQuery("SELECT datasourcedefinition FROM DataSourceDefinition datasourcedefinition", DataSourceDefinition.class).getResultList();
    }
    
}