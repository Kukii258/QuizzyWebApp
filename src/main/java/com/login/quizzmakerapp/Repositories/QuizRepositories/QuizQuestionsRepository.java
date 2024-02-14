package com.login.quizzmakerapp.Repositories.QuizRepositories;

import com.login.quizzmakerapp.Models.Quiz.QuizQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionsRepository extends JpaRepository<QuizQuestions,Long> {

    List<QuizQuestions> findByQuizTableid(long id);

}
