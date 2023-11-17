package com.example.crickettask.service;

import com.example.crickettask.entity.Team;
import com.example.crickettask.entity.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    public List<Team> getAllTeam();

    public Team insertTeam(Team team);

    public Team updateTeam(int id, TeamDTO teamDTO);

    public Team deleteTeam(int id);

}
