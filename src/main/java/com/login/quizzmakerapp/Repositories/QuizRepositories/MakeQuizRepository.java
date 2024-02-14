package com.login.quizzmakerapp.Repositories.QuizRepositories;

import com.login.quizzmakerapp.Models.Quiz.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MakeQuizRepository extends JpaRepository<Quizz,Long> {

    List<Quizz> findByType(boolean type);
    List<Quizz> findByOwnerEmail(String ownerEmail);
    List<Quizz> findByTypeAndOwnerEmailOrName(boolean type,String email,String name);
}
