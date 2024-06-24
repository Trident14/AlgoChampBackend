package dev.IneshSekar.AlgoChamp.model;


public class ScoreResponse {

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
    }
    private String Name;
    private int Score;
    @Override
    public String toString() {
        return "ScoreResponse [Name=" + Name + ", Score=" + Score + "]";
    }
}
