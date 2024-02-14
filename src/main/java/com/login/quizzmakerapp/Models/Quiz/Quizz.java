package com.login.quizzmakerapp.Models.Quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Quizz {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public Quizz(){
        LocalDateTime unFormatedRegisterDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date = unFormatedRegisterDate.format(format);
    }

    private int ownerId;
    private String ownerEmail;
    private String name;
    private boolean type = false;
    private int numberOfQuestions;
    private int timePerQuestion;
    String Date;
    private int numberOfPlays=0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(int timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(int numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }
}
