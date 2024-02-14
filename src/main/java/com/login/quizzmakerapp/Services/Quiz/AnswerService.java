package com.login.quizzmakerapp.Services.Quiz;

import com.login.quizzmakerapp.Models.Quiz.Answers;
import com.login.quizzmakerapp.Repositories.QuizRepositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public List<Answers> getAllAnswers(){return answerRepository.findAll();}

        public void save(Answers answers){
            if(Objects.nonNull(answers)){
                answerRepository.save(answers);
            }
        }

        public Answers getById(Long id){
            Answers answers = null;
            if(Objects.nonNull(id)){
                Optional<Answers> optionalAnswers = answerRepository.findById(id);
                if(optionalAnswers.isPresent()){
                    answers=optionalAnswers.get();
                }else{
                    throw new RuntimeException("Quizz not found with the id " + id);
                }
            }
            return answers;
        }

        public List<Answers> listOfAnswers(int playId) {

            List<Answers> list = answerRepository.findByPlayId(playId);

            return list;
        }

    }


