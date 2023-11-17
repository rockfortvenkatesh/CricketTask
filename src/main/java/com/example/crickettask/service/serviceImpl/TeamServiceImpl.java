package com.example.crickettask.service.serviceImpl;

import com.example.crickettask.entity.Team;
import com.example.crickettask.entity.TeamDTO;
import com.example.crickettask.exception.TeamAlreadyExistsException;
import com.example.crickettask.exception.TeamNotFoundException;
import com.example.crickettask.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crickettask.repo.TeamRepo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Team updateTeam(int id, TeamDTO teamDTO) {
        Optional<Team> optionalTeam = teamRepo.findById(String.valueOf(id));
        if (optionalTeam.isPresent()) {
            Team originalTeam = optionalTeam.get();
            originalTeam.setTeamname(teamDTO.getTeamname());
            return teamRepo.save(originalTeam);
        } else {
            throw new TeamNotFoundException("Team not found for ID: " + id);
        }
    }

    @Override
    public Team deleteTeam(int id) {
        Optional<Team> optionalTeam = teamRepo.findById(String.valueOf(id));
        if (optionalTeam.isPresent()) {
            Team teamToDelete = optionalTeam.get();
            teamRepo.delete(teamToDelete);
            return teamToDelete;
        } else {
            throw new TeamNotFoundException("Team not found for ID: " + id);
        }
    }


}
