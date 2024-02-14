package com.login.quizzmakerapp.Repositories.UserRepositories;

import com.login.quizzmakerapp.Models.User.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats,Long> {

    Optional<UserStats> findByPlayerId(long playerId);


}
