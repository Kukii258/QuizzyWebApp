package com.login.quizzmakerapp.Services.Quiz;

import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import com.login.quizzmakerapp.Models.Quiz.Quizz;
import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Repositories.QuizRepositories.MakeQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MakeQuizService {

    @Autowired
    MakeQuizRepository makeQuizRepository;

    public List<Quizz> getAllQuizzes(){

        List<Quizz> list = makeQuizRepository.findAll();
        Collections.reverse(list);
        return list;
    }

    public void save(Quizz quizz){
        if(Objects.nonNull(quizz)){
            makeQuizRepository.save(quizz);
        }
    }

    public Quizz getById(Long id){
        Quizz quizz = null;
        if(Objects.nonNull(id)){
            Optional<Quizz> optionalQuizz = makeQuizRepository.findById(id);
            if(optionalQuizz.isPresent()){
                quizz=optionalQuizz.get();
            }else{
                throw new RuntimeException("Quizz not found with the id " + id);
            }
        }
        return quizz;
    }

    public List<Quizz> listOfQuizzes(String ownerEmail) {

        List<Quizz> list = makeQuizRepository.findByOwnerEmail(ownerEmail);
        Collections.reverse(list);
        return list;
    }

    public List<Quizz> getByEmailOrName(String email, String name,boolean type){
        List<Quizz> list = makeQuizRepository.findByTypeAndOwnerEmailOrName(type,email,name);
        Collections.reverse(list);
        return list;
    }
    public List<Quizz> getByType(boolean type){
        List<Quizz> list = makeQuizRepository.findByType(type);
        Collections.reverse(list);
        return list;
    }

}
