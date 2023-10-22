package com.example.crickettask.repo;

import com.example.crickettask.entity.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepo extends MongoRepository<Match,String> {

}
