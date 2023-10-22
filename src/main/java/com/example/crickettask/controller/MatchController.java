package com.example.crickettask.controller;

import com.example.crickettask.entity.Match;
import com.example.crickettask.entity.TeamResultsDTO;
import com.example.crickettask.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;
    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/getAllMatch")
    public List<Match> getAllmatch(){
        return matchService.getAllMatch();
    }
    @PostMapping("/startmatch")
    public ResponseEntity<Match> startMatch(@RequestParam String TeamAId, @RequestParam String TeamBId, @RequestParam int Over ,@RequestParam int PlayerSize ){
        Match match  = matchService.StartMatch(TeamAId,TeamBId, Over , PlayerSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(match);
    }

    @GetMapping("/results/{matchId}")
    public ResponseEntity<TeamResultsDTO> getMatchResults(@PathVariable String matchId) {
        TeamResultsDTO matchResult = matchService.getMatchResults(matchId);
        return ResponseEntity.ok(matchResult);
    }
}
