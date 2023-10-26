package com.example.crickettask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "match")
public class Match {
    @Id
    private String id;
    private String teamA;
    private String teamB;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private List<TeamResults> teamResults;
    private String winner;

    public void addTeamResult(String battingTeam, String bowlingTeam,int totalScore,String overs, int wickets, List<String> ballByBall,List<String> playerByPlayer) {
        if (teamResults == null) {
            teamResults = new ArrayList<>();
        }
        TeamResults teamresult = new TeamResults(battingTeam, bowlingTeam, totalScore,overs, wickets, ballByBall ,playerByPlayer);
        teamResults.add(teamresult);
    }
}
