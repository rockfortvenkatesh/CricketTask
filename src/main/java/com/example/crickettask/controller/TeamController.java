package com.example.crickettask.controller;

import com.example.crickettask.entity.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.crickettask.service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@Tag(
        name = "Team Controller all CRUD API",
        description = "This is the class that implements all the crud api related TEAM Management"
)
public class TeamController {

    private final TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status code 200 ok"
    )
    @Operation(
            summary = "Finding all Teams from DB",
            description = "This API if find All teams from DB"
    )
    @GetMapping("/getAllTeam")
    public List<Team> getAllTeam(){
        return teamService.getAllTeam();
    }

    @Operation(
            summary = "Insert Teams to DB",
            description = "This API is Insert a New team to DB"
    )
    @PostMapping("/insertTeam")
    public Team insertTeam(@RequestBody Team team){
        return teamService.insertTeam(team);
    }
    @Operation(
            summary = "Update the Teams in DB",
            description = "This API is Update a team in DB"
    )
    @PutMapping("/update/{id}")
    public Team updateTeam(@PathVariable int id , @RequestBody Team team){
        return teamService.updateTeam(id, team);
    }
    @Operation(
            summary = "Delete the Teams in DB",
            description = "This API is Delete a team in DB"
    )
    @DeleteMapping("/delete/{id}")
    public Team deleteTeam(@PathVariable int id){
        return teamService.deleteTeam(id);
    }
}
