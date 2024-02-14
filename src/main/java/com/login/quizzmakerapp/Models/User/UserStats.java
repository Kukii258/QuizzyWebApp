package com.login.quizzmakerapp.Models.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserStats {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long playerId;
    private String PlayerEmail;//mali p
    private int numberOfQuizPlayed = 0;
    private float CWRatio;
    private int WLRatio;
    private int AverageTimePerQuestion = 0;

    private int win = 0;
    private int lose = 0;
    private int correct = 0;
    private int wrong = 0;

    public int getWLRatio() {
        return WLRatio;
    }

    public void setWLRatio(int WLRatio) {this.WLRatio = WLRatio;}

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerEmail() {
        return PlayerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        PlayerEmail = playerEmail;
    }

    public int getNumberOfQuizPlayed() {
        return numberOfQuizPlayed;
    }

    public void setNumberOfQuizPlayed(int numberOfQuizPlayed) {
        this.numberOfQuizPlayed = numberOfQuizPlayed;
    }

    public float getCWRatio() {
        return CWRatio;
    }

    public void setCWRatio(float CWRatio) {
        this.CWRatio = CWRatio;
    }

    public int getAverageTimePerQuestion() {
        return AverageTimePerQuestion;
    }

    public void setAverageTimePerQuestion(int averageTimePerQuestion) {
        AverageTimePerQuestion = averageTimePerQuestion;
    }
}
