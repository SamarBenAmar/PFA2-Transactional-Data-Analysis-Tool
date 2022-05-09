package com.dravassor.templates;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class InsertLogger extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        routeTemplate("insert-logger")
        .templateParameter("topicIn")
        .from("kafka:{{topicIn}}?brokers=localhost:29092")
        .setHeader("columns").jsonpath("$.payload.after.*~*")
        .setHeader("values").jsonpath("$.payload.after.*")
        .setHeader("db").jsonpath("$.payload.source.db")
        .setHeader("table").jsonpath("$.payload.source.table")
        .log("insert into ${header.db}.${header.table} (${header.columns}) values ${header.values};");
    }
}
