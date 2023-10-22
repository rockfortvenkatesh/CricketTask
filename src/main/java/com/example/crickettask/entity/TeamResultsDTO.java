package com.example.crickettask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamResultsDTO {
    private List<TeamResults> teamResults;
    private String winner;
}
