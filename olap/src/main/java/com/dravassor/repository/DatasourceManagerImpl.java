package com.dravassor.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.dravassor.domain.DataSourceDefinition;
import com.dravassor.service.DataSourceDefinitionService;

@ApplicationScoped
public class DatasourceManagerImpl implements DatasourceManager {

    @Inject
    DataSourceDefinitionService dss;
    
    @Override
    public Connection getConnection(DataSourceDefinition ds) {
        Connection con = null;
        try {
            con = DriverManager.getConnection(ds.getUrl()+ds.getDatabase(),ds.getLogin(),ds.getPassword());
        } catch (Exception e) {
            System.out.println("Error : Failed to get connection !");
        }
        return con;
    }

    @Override
    public Connection getConnection(Long id) {
        Connection con = null;
        try {
            DataSourceDefinition ds = dss.findById(id);
            con = DriverManager.getConnection(ds.getUrl()+ds.getDatabase(),ds.getLogin(),ds.getPassword());
        } catch (Exception e) {
            System.out.println("Error : Failed to get connection !");
        }
        return con;
    }

    @Override
    public Map<String, List<String>> executeQuery(DataSourceDefinition datasource, String sqlQuery) {
        Map<String, List<String>> data = new HashMap<String, List<String>>();
        try(Connection c = getConnection(datasource);){
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                //determiner les colonnes et preparer la map
                int nbCols = rs.getMetaData().getColumnCount();
                List<String> colNames = new ArrayList<String>();
                for(int i=1;i<=nbCols;i++){
                    colNames.add(rs.getMetaData().getColumnName(i));
                    data.put(rs.getMetaData().getColumnName(i),new ArrayList<String>());
                }
                //copier les données (le curseur est sur le premier resultat)
                do{
                    for(int i=1;i<=nbCols;i++){
                        data.get(colNames.get(i-1)).add(rs.getString(i));
                    }
                    //passer a la ligne suivante
                }while(rs.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
