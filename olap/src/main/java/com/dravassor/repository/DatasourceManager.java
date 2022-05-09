package com.dravassor.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.dravassor.domain.DataSourceDefinition;

public interface DatasourceManager {
    public Connection getConnection(DataSourceDefinition ds);  
    public Connection getConnection(Long id) ;
    public Map<String, List<String>> executeQuery(DataSourceDefinition datasource, String sqlQuery);
}