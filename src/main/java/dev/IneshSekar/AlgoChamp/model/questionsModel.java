package dev.IneshSekar.AlgoChamp.model;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "questions")
public class questionsModel {

    
    private String question;
    private List<QuizOption>options;
    
    private Difficulty difficulty;


    public void setQuestion(String question) {
        this.question = question;
    }


    public void setOptions(List<QuizOption> options) {
        this.options = options;
    }


   


    public String getQuestion() {
        return question;
    }


    public List<QuizOption> getOptions() {
        return options;
    }


    public Difficulty getDifficulty() {
        return difficulty;
    }


    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }



    public enum Difficulty {
        Easy,
        Medium,
        Hard
    }
    

    public static class QuizOption {
        private String text;
        private boolean correct;
        public String getText() {
            return text;
        }
        public boolean isCorrect() {
            return correct;
        }
        public void setText(String text) {
            this.text = text;
        }
        public void setCorrect(boolean correct) {
            this.correct = correct;
        }

    }


    @Override
    public String toString() {
        return "questionsModel [question=" + question + ", options=" + options + ", difficulty=" + difficulty + "]";
    }


   


}
