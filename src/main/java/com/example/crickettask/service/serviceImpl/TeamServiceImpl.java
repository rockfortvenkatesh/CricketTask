package com.example.crickettask.service.serviceImpl;

import com.example.crickettask.entity.Team;
import com.example.crickettask.exception.TeamAlreadyExistsException;
import com.example.crickettask.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crickettask.repo.TeamRepo;
import java.util.Collections;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;
    @Override
    public List<Team> getAllTeam() {
        if(teamRepo != null){
            return teamRepo.findAll();
        }else{
            return Collections.emptyList();
        }

    }

    @Override
    public Team insertTeam(Team team) {
        Team existingTeam = teamRepo.findByTeamname(team.getTeamname());
        if (existingTeam != null) {
            throw new TeamAlreadyExistsException("A team with this name already exists.");
        }

        return teamRepo.save(team);
    }

    @Override
    public Team updateTeam(int id, Team team) {
        Team OriginalTeam = teamRepo.findById(String.valueOf(id)).get();
        OriginalTeam.setTeamname(team.getTeamname());
        //OriginalTeam.setTeamsize(team.getTeamsize());
        teamRepo.save(OriginalTeam);
        return OriginalTeam;
    }

    @Override
    public Team deleteTeam(int id) {
        Team team =  teamRepo.findById(String.valueOf(id)).get();
        teamRepo.delete(team);
        return team;
    }

}
