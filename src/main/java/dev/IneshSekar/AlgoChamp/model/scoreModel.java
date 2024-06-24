package dev.IneshSekar.AlgoChamp.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "QuizScore")
public class scoreModel {

    @Id
    public String id;
  
    private String name;
    private int score;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "scoreModel [Name=" + name + ", score=" + score + "]";
    }
   

}
