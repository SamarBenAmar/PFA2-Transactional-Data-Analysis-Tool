package com.dravassor.service;

import java.util.List;

import com.dravassor.api.DTOs.DashboardDataDTO;
import com.dravassor.domain.Dashboard;
//import com.dravassor.domain.DataSourceDefinition;
import com.dravassor.domain.Graph;

public interface DashboardService {

    public Dashboard create(Dashboard dashboard);
    public Dashboard findById(Long idDashboard);
    //public Dashboard findByDb(DataSourceDefinition db);
    public List<Dashboard> findAll();
    public Dashboard update(Dashboard dashboard);
    public void delete(Long idDashboard);
    public Dashboard addGraph(long idDashboard, Graph graph);
    public List<Graph> findAllGraphs(Long idDashboard);
    public Graph findGraphById(Dashboard dashboard, Long idGraph);
    public Graph findGraphByTitle(Long idDashboard, String title);
    public DashboardDataDTO findDataById(Long idDashboard);

    
}
