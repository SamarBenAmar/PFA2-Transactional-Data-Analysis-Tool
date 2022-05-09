package com.dravassor.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

//import com.dravassor.domain.Dashboard;
import com.dravassor.domain.Graph;

@ApplicationScoped
public class GraphServiceImpl implements GraphService {

    @Inject
    EntityManager em;

    @Transactional
    @Override
    public Graph create(Graph graph) {
        em.persist(graph);
        return graph;
    }

    @Override
    public Graph findById(Long idGraph) {
        return em.find(Graph.class, idGraph);
    }

    @Transactional
    @Override
    public Graph findByTitle(String title) {
        return (Graph) em.createQuery("SELECT graph FROM Graph graph WHERE graph.title=:param1",Graph.class).setParameter("param1", title).getResultList();
    }

    @Transactional
    @Override
    public List<Graph> findByType(String type) {
        return (List<Graph>) em.createQuery("SELECT graph FROM Graph graph WHERE graph.type=:param1",Graph.class).setParameter("param1", type).getResultList();
    }

    @Transactional
    @Override
    public List<Graph> findAll() {
       return (List<Graph>) em.createQuery("SELECT graph FROM Graph graph",Graph.class).getResultList();
    }

    @Transactional
    @Override
    public Graph update(Graph graph) {
        em.merge(graph);
        return graph;
    }

    @Transactional
    @Override
    public void delete(Long idGraph) {
        Graph graph = em.find(Graph.class, idGraph);
        if (graph != null){
            em.remove(graph);
        }
    }
    
}
