package com.example.crickettask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamResultsDTO {
    private String teamA;
    private String teamB;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private List<TeamResults> teamResults;
    private String winner;
}
