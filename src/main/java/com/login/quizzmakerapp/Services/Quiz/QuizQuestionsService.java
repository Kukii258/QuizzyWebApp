package com.login.quizzmakerapp.Services.Quiz;

import com.login.quizzmakerapp.Models.Quiz.QuizQuestions;
import com.login.quizzmakerapp.Repositories.QuizRepositories.QuizQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizQuestionsService {

    @Autowired
    QuizQuestionsRepository quizQuestionsRepository;

    public void save(QuizQuestions quizQuestions){
        if(Objects.nonNull(quizQuestions)){
            quizQuestionsRepository.save(quizQuestions);
        }
    }
    public void delete(Long id){
        if(Objects.nonNull(id))
            quizQuestionsRepository.deleteById(id);
    }
    public QuizQuestions getById(Long id){
        QuizQuestions quizQuestions = null;
        if(Objects.nonNull(id)){
            Optional<QuizQuestions> optionalQuizzQuestions = quizQuestionsRepository.findById(id);
            if(optionalQuizzQuestions.isPresent()){
                quizQuestions=optionalQuizzQuestions.get();
            }else{
                throw new RuntimeException("Quizz not found with the id " + id);
            }
        }
        return quizQuestions;
    }
    public List<QuizQuestions> listOfQuestions(long id) {

        List<QuizQuestions> list = quizQuestionsRepository.findByQuizTableid(id);

        return list;
    }
}
