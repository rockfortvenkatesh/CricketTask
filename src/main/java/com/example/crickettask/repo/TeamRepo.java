package com.example.crickettask.repo;

import com.example.crickettask.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends MongoRepository<Team,String> {
 Team findByTeamname(String name);
}
