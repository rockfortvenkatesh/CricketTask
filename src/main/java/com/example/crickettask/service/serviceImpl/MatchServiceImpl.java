package com.example.crickettask.service.serviceImpl;

import com.example.crickettask.entity.Match;
import com.example.crickettask.entity.Team;
import com.example.crickettask.entity.TeamResults;
import com.example.crickettask.entity.TeamResultsDTO;
import com.example.crickettask.exception.MatchNotFoundException;
import com.example.crickettask.exception.TeamNotFoundException;
import com.example.crickettask.repo.MatchRepo;
import com.example.crickettask.repo.TeamRepo;
import com.example.crickettask.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepo matchRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Override
    public List<Match> getAllMatch() {
        return matchRepo.findAll();
    }

    @Override
    public Match StartMatch(String TeamAId, String TeamBId ,int Over ,int PlayerSize) {
        // Match match = matchRepo.findById(id).orElseThrow(() -> new MatchNotFoundException("Match not found"));
       // Match matchh = (Match) matchRepo.findAll();
        Team teamA = teamRepo.findById(TeamAId).orElseThrow(()-> new TeamNotFoundException("TeamA Not found"));
        Team teamB = teamRepo.findById(TeamBId).orElseThrow(()-> new TeamNotFoundException("TeamB Not Found"));
//        Optional<Team> teamAOptional = Optional.ofNullable(teamRepo.findByTeamname(matchh.getTeamA()));
//        Team teamA = teamAOptional.orElseThrow(() -> new TeamNotFoundException("Team" + matchh.getTeamA() + " not found"));
//
//        Optional<Team> teamBOptional = Optional.ofNullable(teamRepo.findByTeamname(matchh.getTeamB()));
//        Team teamB = teamBOptional.orElseThrow(() -> new TeamNotFoundException("Team" + matchh.getTeamB() + " not found"));

        LocalDate MatchDate = LocalDate.now();
        LocalTime MatchTime = LocalTime.now();

        Match match = new Match();
        match.setTeamA(teamA.getTeamname());
        match.setTeamB(teamB.getTeamname());
        match.setMatchDate(MatchDate);
        match.setMatchTime(MatchTime);

        int totalBalls = Over*6;

        play(match, teamA, teamB, totalBalls, PlayerSize);
        play(match, teamB, teamA, totalBalls, PlayerSize);

        int teamAScore = calculateTeamScore(match, teamA.getTeamname());
        int teamBScore = calculateTeamScore(match, teamB.getTeamname());


        String winner = (teamAScore > teamBScore) ? teamA.getTeamname() : teamB.getTeamname();
        match.setWinner(winner);

        matchRepo.save(match);
        return match;
    }



    private int calculateTeamScore(Match match, String teamname) {
   int totalScore = 0 ;
   for(TeamResults teamResults : match.getTeamResults()){
       if(teamname.equals(teamResults.getBattingTeam())){
           totalScore += teamResults.getTotalScore();
       }
   }
   return  totalScore;
    }

    private void play(Match match, Team BattingTeam, Team BowlingTeam, int totalBalls, int maxWickets) {
        int totalScore = 0;
        int wicketCount = 0;
        List<String> ballByBall = new ArrayList<>();

        for (int ball = 1; ball <= totalBalls; ball++) {
            if (wicketCount == maxWickets) {
                break;
            }

            int ballResult = BallResult();
            if (ballResult == 7) {
                wicketCount++;
                ballByBall.add("W");
            } else {
                totalScore += ballResult;
                ballByBall.add(String.valueOf(ballResult));
            }
        }

        match.addTeamResult(BattingTeam.getTeamname(), totalScore, wicketCount, ballByBall);
    }

    private int BallResult() {

        return (int) (Math.random() * 8);
    }

    @Override
    public TeamResultsDTO getMatchResults(String matchId) {
        Match match = matchRepo.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));

        TeamResultsDTO matchResult = new TeamResultsDTO();
        matchResult.setTeamResults(match.getTeamResults());
        matchResult.setWinner(match.getWinner());

        return matchResult;
    }
}