package com.example.crickettask.controller;

import com.example.crickettask.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.crickettask.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("/getAllTeam")
    public List<Team> getAllTeam(){
        return teamService.getAllTeam();
    }

    @PostMapping("/insertTeam")
    public Team insertTeam(@RequestBody Team team){
        return teamService.insertTeam(team);
    }

    @PutMapping("/update/{id}")
    public Team updateTeam(@PathVariable int id , @RequestBody Team team){
        return teamService.updateTeam(id, team);
    }

    @DeleteMapping("/delete/{id}")
    public Team deleteTeam(@PathVariable int id){
        return teamService.deleteTeam(id);
    }
}
