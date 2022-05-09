package com.dravassor.api.DTOs;

import java.util.List;
import java.util.Map;

import com.dravassor.domain.Graph;

import lombok.Data;

@Data
public class GraphDataDTO {
    String type;
    String title;
    String xAxisTitle;
    String yAxisTitle;
    String sqlQuery;
    long idDatasourceDefinition;
    Map<String,List<String>> data;
}
