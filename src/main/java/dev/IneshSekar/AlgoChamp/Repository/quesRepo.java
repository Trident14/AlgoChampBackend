package dev.IneshSekar.AlgoChamp.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.IneshSekar.AlgoChamp.model.questionsModel;
import dev.IneshSekar.AlgoChamp.model.questionsModel.Difficulty;

public interface quesRepo extends MongoRepository<questionsModel,String>
{
    List<questionsModel>findByDifficulty(Difficulty difficulty);
}
