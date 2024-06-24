package dev.IneshSekar.AlgoChamp.controller;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.IneshSekar.AlgoChamp.Repository.quesRepo;
import dev.IneshSekar.AlgoChamp.model.questionsModel;
import dev.IneshSekar.AlgoChamp.model.questionsModel.Difficulty;

@RestController
@CrossOrigin
public class GetquestionController {

    
    

    @RequestMapping(value  = "/")
    public  String hello(){
        return "hello from server";
    }

    @Autowired
    quesRepo repo;

    // return any 10 question
    @GetMapping("/questions/random")
    public List<questionsModel> getQuestion(){
        List<questionsModel>Questions = repo.findAll();
        Collections.shuffle(Questions);
      
        return Questions.subList(0, 10);
    }

    // return 10 random question with difficulty
    @GetMapping("/questions/{difficulty}")
    public List<questionsModel> getQuestionsByDifficulty(@PathVariable Difficulty difficulty) {
        List<questionsModel>questionArray =  repo.findByDifficulty(difficulty);
        Collections.shuffle(questionArray);
        if(questionArray.size()<10){
            return questionArray;
        }
        return questionArray.subList(0, 10);

    }
    
  
    @PostMapping("/add")
    public questionsModel addQuestion(@RequestBody questionsModel question) {
        return repo.save(question);
    }

   
    
}
