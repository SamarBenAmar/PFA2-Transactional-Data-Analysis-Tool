package com.dravassor.service;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.dravassor.api.DTOs.DashboardDataDTO;
import com.dravassor.api.DTOs.GraphDataDTO;
import com.dravassor.domain.Dashboard;
//import com.dravassor.domain.DataSourceDefinition;
import com.dravassor.domain.Graph;
import com.dravassor.repository.DatasourceManager;

@ApplicationScoped
public class DashboardServiceImpl implements DashboardService {

    @Inject
    EntityManager em;

    @Inject
    DatasourceManager datasourceManager;

    @Transactional
    @Override
    public Dashboard create(Dashboard dashboard) {
        em.persist(dashboard);
        return dashboard;
    }
    @Transactional
    @Override
    public Dashboard findById(Long idDashboard) {
        return em.find(Dashboard.class,idDashboard);
    }

    @Override
    public DashboardDataDTO findDataById(Long idDashboard) {
        Dashboard d = findById(idDashboard);
        DashboardDataDTO ddto = new DashboardDataDTO();
        ddto.setTitle(d.getTitle());
        ddto.setId(idDashboard);
        for(Graph g : d.getGraphs()){
            Map<String,List<String>> data = datasourceManager.executeQuery(g.getDatasource(),g.getSqlQuery());
            GraphDataDTO gdto = new GraphDataDTO();
            gdto.setSqlQuery(g.getSqlQuery());
            gdto.setTitle(g.getTitle());
            gdto.setType(g.getType());
            gdto.setXAxisTitle(g.getXAxisTitle());
            gdto.setYAxisTitle(g.getYAxisTitle());
            gdto.setData(data);
            ddto.addGraph(gdto);
        }
        return ddto;
    }

 /*    @Transactional
    @Override
    public Dashboard findByDb(DataSourceDefinition db) {
        return (Dashboard) em.createQuery("SELECT dashboard FROM Dashboard dashboard WHERE dashboard.db=:param1",Dashboard.class).setParameter("param1", db).getResultList();
    } */

  
    @Override
    @Transactional
    public List<Dashboard> findAll() {
        return em.createQuery("SELECT dashboard FROM Dashboard dashboard",Dashboard.class).getResultList();
    }

    @Override
    @Transactional
    public Dashboard update(Dashboard dashboard) {
        em.merge(dashboard);
        return dashboard;
    }
    
    @Transactional
    @Override
    public void delete(Long idDashboard) {
        Dashboard dashboard = em.find(Dashboard.class, idDashboard);
        if (dashboard != null){
            em.remove(dashboard);   
        }
    }

    
    @Transactional
     @Override
     public Dashboard addGraph(long idDashboard, Graph graph) {
        Dashboard d = findById(idDashboard);
        d.addGraph(graph);
        em.merge(d);
        return d;
     }

     @Transactional
     @Override
     public List<Graph> findAllGraphs(Long idDashboard) {
         return (List<Graph>) em.createQuery("SELECT graph FROM Graph graph WHERE graph.idDashboard=:param1",Graph.class).setParameter("param1", idDashboard).getResultList();
     }

     @Transactional
     @Override
     public Graph findGraphById(Dashboard dashboard, Long idGraph) {
         Query query = em.createQuery("SELECT graph FROM Graph graph WHERE graph.dashboard=:param1 AND graph.idGraph=:param2",Graph.class);
         Graph graph = (Graph) query.setParameter("param1", dashboard).setParameter("param2", idGraph).getResultList();
         return graph;
     }

     @Transactional
     @Override
     public Graph findGraphByTitle(Long idDashboard, String graphTitle) {
         Query query = em.createQuery("SELECT graph FROM Graph graph WHERE graph.idDashboard=:param1 AND graph.title=:param2",Graph.class);
         Graph graph = (Graph) query.setParameter("param1", idDashboard).setParameter("param2", graphTitle).getResultList();
         return graph;
     }

    
}
