package com.login.quizzmakerapp.Repositories.QuizRepositories;

import com.login.quizzmakerapp.Models.Quiz.Answers;
import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizStatsRepository extends JpaRepository<QuizStats, Long> {

    Optional<QuizStats> findByPlayerId(long playerId);
    List<QuizStats> getByPlayerId(long playerId);
    List<QuizStats> findByQuizId(long quizId);
    List<QuizStats> findByQuizIdAndPlayerId(long quizId, long playerId);


}
