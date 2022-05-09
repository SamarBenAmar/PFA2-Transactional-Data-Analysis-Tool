package com.dravassor.templates;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Router extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        routeTemplate("router")
            .templateParameter("topicIn")
            .templateParameter("topicOut")
                .from("kafka:{{topicIn}}?brokers=localhost:29092")
                .to("kafka:{{topicOut}}?brokers=localhost:29092");
        }
}
