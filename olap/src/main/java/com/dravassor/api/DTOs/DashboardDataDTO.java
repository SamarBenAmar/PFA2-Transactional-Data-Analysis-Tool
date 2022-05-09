package com.dravassor.api.DTOs;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DashboardDataDTO {
    private long id;
    private String title;
    private List<GraphDataDTO> graphs = new ArrayList<GraphDataDTO>();
    public void addGraph(GraphDataDTO gdto) {
        graphs.add(gdto);
    }
}
