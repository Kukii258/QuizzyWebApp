package com.login.quizzmakerapp.Models.Quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class QuizQuestions {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public QuizQuestions(){}

    private Long quizTableid;
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    private String corectAnswer;
    private String userAnswer = "9";

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizTableid() {
        return quizTableid;
    }

    public void setQuizTableid(Long quizTableid) {
        this.quizTableid = quizTableid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public String getCorectAnswer() {
        return corectAnswer;
    }

    public void setCorectAnswer(String corectAnswer) {
        this.corectAnswer = corectAnswer;
    }
}
