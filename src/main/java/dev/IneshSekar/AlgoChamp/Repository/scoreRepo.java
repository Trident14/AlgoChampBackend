package dev.IneshSekar.AlgoChamp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.IneshSekar.AlgoChamp.model.scoreModel;


public interface scoreRepo  extends MongoRepository<scoreModel,String>{
    scoreModel findByName(String name);
    List<scoreModel> findByOrderByScoreDesc();
    

    
    
}
