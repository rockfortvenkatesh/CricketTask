package com.example.crickettask.service;

import com.example.crickettask.entity.Match;
import com.example.crickettask.entity.TeamResultsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {

   public List<Match> getAllMatch();
   public Match StartMatch(String TeamAId, String TeamBId ,int Over , int PlayerSize);

   public TeamResultsDTO getMatchResults(String matchId);

}
