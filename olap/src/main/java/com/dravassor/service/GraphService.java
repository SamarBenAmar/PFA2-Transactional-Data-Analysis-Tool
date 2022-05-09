package com.dravassor.service;

import java.util.List;


import com.dravassor.domain.Graph;

public interface GraphService {

    public Graph create(Graph graph);
    public Graph findById(Long idGraph);
    public Graph findByTitle(String title);
    public List<Graph> findByType(String type);
    public List<Graph> findAll();
    public Graph update(Graph graph);
    public void delete(Long idGraph);

}
