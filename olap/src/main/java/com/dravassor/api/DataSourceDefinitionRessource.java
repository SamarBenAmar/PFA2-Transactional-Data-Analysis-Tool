package com.dravassor.api;


import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.dravassor.api.commands.CreateDatasourceDefinitionCommand;
import com.dravassor.domain.DataSourceDefinition;
import com.dravassor.service.DataSourceDefinitionService;

@Path("/datasources")
@Transactional
public class DataSourceDefinitionRessource {

    @Inject
    DataSourceDefinitionService dss;

    @GET
    @Path("/{id}")
    public DataSourceDefinition findByIdDataSourceDefinition(@PathParam("id") long id) {
        return dss.findById(id); 
    }

    @POST
    public DataSourceDefinition createDataSourceDefinition(CreateDatasourceDefinitionCommand cmd) {
        DataSourceDefinition ddef = new DataSourceDefinition();
        ddef.setDatabase(cmd.getDatabase());
        ddef.setDriver(cmd.getDriver());
        ddef.setKind(cmd.getKind());
        ddef.setUrl(cmd.getUrl());
        ddef.setLabel(cmd.getLabel());
        ddef.setLogin(cmd.getLogin());
        ddef.setPassword(cmd.getPassword());
        return dss.create(ddef);
    }

    @PUT
    public DataSourceDefinition updateDataSourceDefinition(DataSourceDefinition ds) {

        return dss.update(ds);
    }


    @GET
    public List<DataSourceDefinition> dataSourceDefinitions() {
        return dss.findAll();
    }


    
}