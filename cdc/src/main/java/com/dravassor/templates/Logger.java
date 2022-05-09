package com.dravassor.templates;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Logger extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        routeTemplate("logger")
            .templateParameter("topicIn")
            .from("kafka:{{topicIn}}?brokers=localhost:29092")
            .log("${body}");
    }
}
