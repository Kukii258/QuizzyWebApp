package com.login.quizzmakerapp.Services.Quiz;

import com.login.quizzmakerapp.Models.Quiz.QuizQuestions;
import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import com.login.quizzmakerapp.Models.User.UserStats;
import com.login.quizzmakerapp.Repositories.QuizRepositories.QuizStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizStatsService {

    @Autowired
    QuizStatsRepository quizStatsRepository;

    public void save(QuizStats quizStats){
        if(Objects.nonNull(quizStats)){
            quizStatsRepository.save(quizStats);
        }
    }
    public void delete(Long id){
        if(Objects.nonNull(id))
            quizStatsRepository.deleteById(id);
    }
    public QuizStats getById(Long id){
        QuizStats quizStats = null;
        if(Objects.nonNull(id)){
            Optional<QuizStats> optionalQuizStats = quizStatsRepository.findById(id);
            if(optionalQuizStats.isPresent()){
                quizStats=optionalQuizStats.get();
            }else{
                throw new RuntimeException("Quizz not found with the id " + id);
            }
        }
        return quizStats;
    }

    public QuizStats getByPlayerId(Long id){
        QuizStats quizStats = null;
        if(Objects.nonNull(id)){
            Optional<QuizStats> optionalQuizStats = quizStatsRepository.findByPlayerId(id);
            if(optionalQuizStats.isPresent()){
                quizStats=optionalQuizStats.get();
            }else{
                throw new RuntimeException("Quiz not found with the id " + id);
            }
        }
        return quizStats;
    }


    public List<QuizStats> listOfQuizStats(long quizId) {

        List<QuizStats> list = quizStatsRepository.findByQuizId(quizId);

        return list;
    }
    public List<QuizStats> listOfQuizStatsAdd(long quizId,long playerId) {

        List<QuizStats> list = quizStatsRepository.findByQuizIdAndPlayerId(quizId,playerId);

        return list;
    }

    public List<QuizStats> listOfPlayerQuizes(long playerId) {
        List<QuizStats> list = quizStatsRepository.getByPlayerId(playerId);
        Collections.reverse(list);
        return list;
    }

}
