package com.dravassor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.TemplatedRouteBuilder;

@Path("/cdc")
public class CDCResource {
    @Inject
    CamelContext context;
    
    @Path("/start")
    @GET
    public String startProducers() {
        TemplatedRouteBuilder.builder(context, "router")
            .parameter("id", "router_customers")
            .parameter("topicIn", "postgres.public.customers")
            .parameter("topicOut", "northwind-cdc-data")
            .add();
        TemplatedRouteBuilder.builder(context, "router")
            .parameter("id", "router_categories")
            .parameter("topicIn", "postgres.public.categories")
            .parameter("topicOut", "northwind-cdc-data")
            .add();
        TemplatedRouteBuilder.builder(context, "jsonpath-filter")
            .parameter("topicIn", "northwind-cdc-data")
            .parameter("topicOut", "insertions")
            .parameter("predicate", "$[?(@.payload.op == 'c')]")
            .add();
        TemplatedRouteBuilder.builder(context, "jsonpath-filter")
            .parameter("topicIn", "northwind-cdc-data")
            .parameter("topicOut", "updates")
            .parameter("predicate", "$[?(@.payload.op == 'u')]")
            .add();
        TemplatedRouteBuilder.builder(context, "insert-logger")
            .parameter("topicIn", "insertions")
            .add();
        // TemplatedRouteBuilder.builder(context, "logger")
        //     .parameter("topicIn", "insertions")
        //     .add();
        // TemplatedRouteBuilder.builder(context, "logger")
        //     .parameter("topicIn", "updates")
        //     .add();
        return "done";
    }
    

    @Path("/routes")
    @GET
    public List<String> listRouteIds() {
        return context.getRoutes().stream().map(Route::getId).sorted().collect(Collectors.toList());
    }
}