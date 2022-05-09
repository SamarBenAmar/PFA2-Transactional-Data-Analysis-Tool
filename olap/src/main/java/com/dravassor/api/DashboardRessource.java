package com.dravassor.api;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dravassor.api.DTOs.DashboardDataDTO;
import com.dravassor.api.commands.AddGraphToDashboardCommand;
import com.dravassor.api.commands.CreateDashboardCommand;
import com.dravassor.domain.Dashboard;
import com.dravassor.domain.DataSourceDefinition;
// import com.dravassor.domain.DataSourceDefinition;
import com.dravassor.domain.Graph;
import com.dravassor.service.DashboardService;
import com.dravassor.service.DataSourceDefinitionService;
import com.dravassor.service.GraphService;

@Path("/dashboards")
@Transactional
public class DashboardRessource {

    @Inject 
    DashboardService dashboardService;
    @Inject
    DataSourceDefinitionService datasourceDefinitionService;

    @Inject
    GraphService graphService;

    @GET
    @Path("/{idDashboard}")
    public Dashboard findByIdDashboard(@PathParam("idDashboard") Long idDashboard) {
        return dashboardService.findById(idDashboard); 
    }
    @GET
    @Path("/{idDashboard}/data")
    public DashboardDataDTO getDashboardDataById(@PathParam("idDashboard") Long idDashboard) {
        return dashboardService.findDataById(idDashboard); 
    }

    /* @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{db}")
    public Dashboard findByDbDashboard(@PathParam("db") DataSourceDefinition db) {
        return dashboardService.findByDb(db); 
    }
 */
    @POST
    public Dashboard createDashboard(CreateDashboardCommand cmd){
        Dashboard d = new Dashboard();
        d.setTitle(cmd.getTitle());
        return dashboardService.create(d);
    }

    @GET
    public List<Dashboard> dashboards() {
        return dashboardService.findAll();
    }

    @POST
    @Path("/{idDashboard}")
    public Dashboard addGraphToDashboard(@PathParam("idDashboard") long idDashboard,AddGraphToDashboardCommand cmd){
        Graph g = new Graph();
        g.setSqlQuery(cmd.getSqlQuery());
        g.setTitle(cmd.getTitle());
        g.setType(cmd.getType());
        g.setXAxisTitle(cmd.getXAxisTitle());
        g.setYAxisTitle(cmd.getYAxisTitle());
        DataSourceDefinition d = datasourceDefinitionService.findById(cmd.getIdDatasourceDefinition());
        g.setDatasource(d);
        return dashboardService.addGraph(idDashboard,g);
    }

    @PUT
    public Dashboard updateDashboard(Dashboard dashboard) {
        return dashboardService.update(dashboard);
    }

    @DELETE
    public void deleteDashboard(Long idDashboard){
        dashboardService.delete(idDashboard);
    }

    @GET
    @Path("/{idDashboard}/graphs")
    public List<Graph> findAllGraphsInDashboard(@PathParam("idDashboard") Long idDashboard){
        return dashboardService.findAllGraphs(idDashboard);
    } 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idDashboard}/graphs/{idGraph}")
    public Graph findGraphByIdInDashboard(@PathParam("idDashboard") Long idDashboard, @PathParam("idGraph") Long idGraph){
        Dashboard dashboard = findByIdDashboard(idDashboard);
        return dashboardService.findGraphById(dashboard, idGraph);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idDashboard}/graphs/{graphTitle}")
    public Graph findGraphByTitleDashboard(@PathParam("idDashboard") Long idDashboard, @PathParam("graphTitle") String graphTitle){
        return dashboardService.findGraphByTitle(idDashboard, graphTitle);
    } 


}
