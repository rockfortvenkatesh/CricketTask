package com.example.crickettask.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamResults {
    private String battingTeam;
    private int totalScore;
    private int wickets;
    private List<String> ballByBall;

}
