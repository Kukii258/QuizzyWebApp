package com.login.quizzmakerapp.Models.Quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Answers {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private long playerId;
    private long quizId;
    private long quizQuestionId;
    private String playerAnswer;
    private String correctAnswer;
    private int playId;
    private String question;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPlayId() {
        return playId;
    }

    public void setPlayId(int playId) {
        this.playId = playId;
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

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public long getQuizQuestionId() {
        return quizQuestionId;
    }

    public void setQuizQuestionId(long quizQuestionId) {
        this.quizQuestionId = quizQuestionId;
    }

    public String getPlayerAnswer() {
        return playerAnswer;
    }

    public void setPlayerAnswer(String playerAnswer) {
        this.playerAnswer = playerAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
