package com.login.quizzmakerapp.Repositories.UserRepositories;

import com.login.quizzmakerapp.Models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);



}
