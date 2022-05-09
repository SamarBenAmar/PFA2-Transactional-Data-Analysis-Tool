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

import com.dravassor.domain.Graph;
import com.dravassor.service.GraphService;

@Path("/graphs")
@Transactional
public class GraphRessource {

    @Inject
    GraphService graphService;

    @GET
    @Path("/{id}")
    public Graph findByIdGraph(@PathParam("id") Long idGraph) {
        return graphService.findById(idGraph); 
    }

    @GET
    @Path("/{title}")
    public Graph findByTitleGraph(@PathParam("title") String title) {
        return graphService.findByTitle(title); 
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{type}")
    public List<Graph> findByTypeGraph(@PathParam("type") String type) {
        return graphService.findByType(type); 
    }


    @POST
    public Graph createGraph(Graph graph){
        return graphService.create(graph);
    }

    @PUT
    public Graph updateGraph(Graph graph) {

        return graphService.update(graph);
    }

    @GET
    public List<Graph> graphs() {
        return graphService.findAll();
    }

    @DELETE
    public void deleteGraph(Long idGraph){
        graphService.delete(idGraph);
    }
}
  