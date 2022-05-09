package com.dravassor.api.commands;

import java.util.Calendar;
import java.util.Date;

import lombok.Data;

@Data
public class CreateDatasourceDefinitionCommand {
    String label;
    String kind;
    String driver;
    String login;
    String password;
    String url;
    String database;
    Date lastUpdate = Calendar.getInstance().getTime();
}
