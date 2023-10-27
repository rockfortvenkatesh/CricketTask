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
import java.time.format.DateTimeFormatter;
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
        Team teamA = teamRepo.findById(TeamAId).orElseThrow(()-> new TeamNotFoundException("TeamA Not found"));
        Team teamB = teamRepo.findById(TeamBId).orElseThrow(()-> new TeamNotFoundException("TeamB Not Found"));

        LocalDate MatchDate = LocalDate.now();
        LocalTime MatchTime = LocalTime.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String customTime = MatchTime.format(customFormatter);

        Match match = new Match();
        match.setTeamA(teamA.getTeamname());
        match.setTeamB(teamB.getTeamname());
        match.setMatchDate(MatchDate);
        match.setMatchTime(LocalTime.parse(customTime,customFormatter));

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
        List<String> playerByPlayer = new ArrayList<>();
        List<String> overByOver  = new ArrayList<>();
        int curr_player = 0 ;
        int ballsInOver = 0 ;
        int overCount = 1 ;
        int overRuns = 0 ;
        String overs = Integer.toString(totalBalls/6);

        for (int ball = 1; ball <= totalBalls; ball++) {
            if (wicketCount == maxWickets) {
                System.out.println("Total ball taken : "+ball);
                int UsedOver = ball/6;
                System.out.println("Used over "+UsedOver);
                int UsedBalls = ball%6;
                System.out.println("Used balls "+UsedBalls);
                overs = UsedOver+" Overs "+(UsedBalls-1)+" Balls ";
                break;
            }
            int ballResult = BallResult();
            if (ballResult == 7) {
                wicketCount++;
                if(curr_player < playerByPlayer.size()){
                    int playerscore = Integer.parseInt(playerByPlayer.get(curr_player));
                    playerByPlayer.set(curr_player,String.valueOf(playerscore));
                }else{
                    playerByPlayer.add(String.valueOf(0));
                }
                ballByBall.add("Batsman " + (curr_player+1) +" - Ball " + ball +" : "+"W");
                ballsInOver++;
                System.out.println("in wicket part also increase the ball count "+ballsInOver);
                curr_player = (curr_player+1)%maxWickets;

            } else {

                if (ballsInOver == 6) {
                    overByOver.add(overRuns + " runs in "+overCount+ " over");
                    overCount++;
                    overRuns = 0;
                    ballsInOver = 0;
                }
                    if (curr_player < playerByPlayer.size()) {
                        int playerscore = Integer.parseInt(playerByPlayer.get(curr_player));
                        playerByPlayer.set(curr_player, String.valueOf(playerscore + ballResult));
                    } else {
                        playerByPlayer.add(String.valueOf(ballResult));
                    }
                    ballByBall.add("Batsman " + (curr_player + 1) + " - Ball " + ball + " : " + ballResult);
                    ballsInOver++;
                System.out.println("Ball in over :"+ ballsInOver);
                    overRuns += ballResult;
                System.out.println("Current Over result :" + overRuns);
                    totalScore += ballResult;
            }
        }
        if(ballsInOver <= 6){
            overByOver.add(overRuns+" runs in "+overCount+" over");
        }
        match.addTeamResult(BattingTeam.getTeamname(),BowlingTeam.getTeamname(), totalScore,overs,wicketCount, ballByBall , playerByPlayer , overByOver);
    }

    private int BallResult() {

        return (int) (Math.random() * 8);
    }

    @Override
    public TeamResultsDTO getMatchResults(String matchId) {
        Match match = matchRepo.findById(matchId)
                .orElseThrow(() -> new MatchNotFoundException("Match not found"));

        TeamResultsDTO matchResult = new TeamResultsDTO();
        matchResult.setTeamA(match.getTeamA());
        matchResult.setTeamB(match.getTeamB());
        matchResult.setMatchDate((match.getMatchDate()));
        matchResult.setMatchTime((match.getMatchTime()));
        matchResult.setTeamResults(match.getTeamResults());
        matchResult.setWinner(match.getWinner());

        return matchResult;
    }
}
