package com.dravassor.domain;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;

import lombok.Data;
import net.bytebuddy.dynamic.TypeResolutionStrategy.Lazy;

@Data
@Entity
public class Graph {

    @Id
    @GeneratedValue
    protected Long idGraph;
    protected String type = "default";
    protected String title;
    protected String xAxisTitle;
    protected String yAxisTitle;
    protected String sqlQuery;
    @ManyToOne
    protected DataSourceDefinition datasource;
    @JsonIgnore
    @ManyToOne
    Dashboard dashboard;
}
