package com.dravassor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Dashboard {
    @Id
    @GeneratedValue
    protected Long idDashboard;
    String title;
    @OneToMany(
        mappedBy = "dashboard",
        cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        orphanRemoval = true)
    List<Graph> graphs = new ArrayList<Graph>();

    public void addGraph(Graph g){
        graphs.add(g);
        g.setDashboard(this);
    }
    public void removeGraph(Graph g) {
        graphs.remove(g);
        g.setDashboard(null);
    }
}
