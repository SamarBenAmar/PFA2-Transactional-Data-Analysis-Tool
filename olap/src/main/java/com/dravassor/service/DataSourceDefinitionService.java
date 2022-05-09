package com.dravassor.service;


import java.util.List;

import com.dravassor.domain.DataSourceDefinition;

public interface DataSourceDefinitionService {
    public DataSourceDefinition create(DataSourceDefinition ds);
    public DataSourceDefinition findById(Long id);
    public List<DataSourceDefinition> findByLabel(String label);
    public DataSourceDefinition update(DataSourceDefinition ds);
    public List<DataSourceDefinition> findAll();
}
