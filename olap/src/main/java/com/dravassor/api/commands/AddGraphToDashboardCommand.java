package com.dravassor.api.commands;

import lombok.Data;

@Data
public class AddGraphToDashboardCommand {
     String type = "default";
     String title;
     String xAxisTitle;
     String yAxisTitle;
     String sqlQuery;
     long idDatasourceDefinition;
}
