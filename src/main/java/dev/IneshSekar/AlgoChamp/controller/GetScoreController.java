package dev.IneshSekar.AlgoChamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.IneshSekar.AlgoChamp.Repository.scoreRepo;
import dev.IneshSekar.AlgoChamp.model.scoreModel;

@RestController
@CrossOrigin
public class GetScoreController {
    @Autowired
    private  scoreRepo scores;

    //To fetch score in decreasing order
    @GetMapping("/point")
    @ResponseBody
    public List<scoreModel>getScore(){
        List<scoreModel> topScore = scores.findByOrderByScoreDesc();
        return topScore;
    }


    //Add Score to database
    @PostMapping("/AddScore")
    public ResponseEntity<String> addScore(@RequestBody scoreModel data) {
        try {
            List<scoreModel> topScores = scores.findByOrderByScoreDesc();
  
            scoreModel oldScore = scores.findByName(data.getName());

         
            if (topScores.size() < 5) {
                if (oldScore != null) {
                    if (oldScore.getScore() < data.getScore()) {
                        oldScore.setScore(data.getScore());
                        scores.save(oldScore);
                        return ResponseEntity.status(200).body("Score Updated");
                    } else {
                        scores.save(oldScore);
                        return ResponseEntity.status(200).body("Found Higher score");
                    }
                } else {
                    scoreModel newScore = scores.save(data);
                    return ResponseEntity.status(200).body("Added score for: " + newScore.getName());
                }
            } else {

                if (oldScore != null  ) {
                    if(oldScore.getScore() < data.getScore()){
                        oldScore.setScore(data.getScore());
                        oldScore.setName(data.getName());
                        scores.save(oldScore);
                        return ResponseEntity.status(200).body("Score Updated");
                    }
                    else{
                        return ResponseEntity.status(200).body("High Score already exist");
                    }
                } else {
                 
                    scoreModel lowestScore = topScores.get(4);
                   
                    if (lowestScore.getScore() < data.getScore()) {
                        topScores.remove(lowestScore);
                        topScores.add(data);
                        scores.deleteAll(); // Delete all existing documents
                        scores.saveAll(topScores); // Save the new data
                    }
                    return ResponseEntity.status(200).body("Successfully Updated");
                }
                
            }
        } catch (DataAccessException e) {
            String errorResponse = "An error occurred while processing the request: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (IndexOutOfBoundsException e) {
            String errorResponse = "An error occurred while processing the request Oob: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        } catch (NullPointerException e) {
            String errorResponse = "An error occurred: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
