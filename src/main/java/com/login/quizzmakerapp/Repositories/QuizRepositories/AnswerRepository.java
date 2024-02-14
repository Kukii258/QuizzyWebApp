package com.login.quizzmakerapp.Repositories.QuizRepositories;

import com.login.quizzmakerapp.Models.Quiz.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers,Long> {

    List<Answers> findByQuizId(Long id);
    List<Answers> findByPlayId(int id);

}
