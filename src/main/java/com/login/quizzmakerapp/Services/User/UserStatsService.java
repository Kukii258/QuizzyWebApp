package com.login.quizzmakerapp.Services.User;

import com.login.quizzmakerapp.Models.Quiz.Answers;
import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import com.login.quizzmakerapp.Models.Quiz.Quizz;
import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Models.User.UserStats;
import com.login.quizzmakerapp.Repositories.UserRepositories.UserStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserStatsService {


    @Autowired
    UserStatsRepository userStatsRepository;

    public List<UserStats> getAllUsersStats(){return userStatsRepository.findAll();}

    public void save(UserStats userStats){
        if(Objects.nonNull(userStats)) {
            userStatsRepository.save(userStats);
        }
    }
    public UserStats getById(Long id){
        UserStats userStats = null;
        if(Objects.nonNull(id)){
            Optional<UserStats> optionalUserStats = userStatsRepository.findById(id);
            if(optionalUserStats.isPresent()){
                userStats=optionalUserStats.get();
            }else{
                throw new RuntimeException("User not found with the id " + id);
            }
        }
        return userStats;
    }

    public UserStats getByPlayerId(Long id){
        UserStats userStats = null;
        if(Objects.nonNull(id)){
            Optional<UserStats> optionalUserStats = userStatsRepository.findByPlayerId(id);
            if(optionalUserStats.isPresent()){
                userStats=optionalUserStats.get();
            }else{
                throw new RuntimeException("User not found with the id " + id);
            }
        }
        return userStats;
    }

    public void setUserStats(User user){
        UserStats userStats = new UserStats();
        userStats.setPlayerId(user.getId());
        userStats.setPlayerEmail(user.getEmail());
        userStatsRepository.save(userStats);
    }



}
