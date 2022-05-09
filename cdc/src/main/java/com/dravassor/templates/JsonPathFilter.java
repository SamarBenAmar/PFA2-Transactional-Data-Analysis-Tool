package com.dravassor.templates;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class JsonPathFilter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        routeTemplate("jsonpath-filter")
            .templateParameter("topicIn")
            .templateParameter("topicOut")
            .templateParameter("predicate")
                .from("kafka:{{topicIn}}?brokers=localhost:29092")
                .filter().jsonpath("{{predicate}}")
                .to("kafka:{{topicOut}}?brokers=localhost:29092");
        }
}
