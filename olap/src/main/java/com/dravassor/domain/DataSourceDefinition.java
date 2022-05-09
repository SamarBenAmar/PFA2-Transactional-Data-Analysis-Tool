package com.dravassor.domain;


import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;



@Data
@Entity
public class DataSourceDefinition {
    @Id
    @GeneratedValue
    private Long id;
    private String label;
    private String kind;
    private String driver;
    private String login;
    private String password;
    private String url;
    private String database;
    private Date lastUpdate = Calendar.getInstance().getTime();

    public DataSourceDefinition() {

    }

   
}
