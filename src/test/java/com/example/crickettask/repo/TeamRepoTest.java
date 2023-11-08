package com.example.crickettask.repo;

import com.example.crickettask.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class TeamRepoTest {

@Autowired
    private TeamRepo teamRepo;
     Team team;

    @BeforeEach
    void setUp() {
        team = new Team("1","CSK");
        teamRepo.save(team);
    }

    @AfterEach
    void tearDown() {
        team  = null;
        teamRepo.deleteAll();
    }

    //Test case Success
     @Test
    void testFindByTeamName_Found(){
        List<Team> teamList = (List<Team>) teamRepo.findByTeamname("CSK");
        assertThat(teamList.get(0).getId()).isEqualTo(team.getId());
     }

    //Test case Failure
//    @Test
//    void testFindByTeamName_NotFound()
//    {
//        List<Team> teamList = (List<Team>) teamRepo.findByTeamname("RR");
//        assertThat(teamList.isEmpty()).isFalse();
//    }
}
