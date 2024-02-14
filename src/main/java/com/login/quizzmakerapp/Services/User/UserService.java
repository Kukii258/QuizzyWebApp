package com.login.quizzmakerapp.Services.User;

import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Repositories.UserRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsers(){return userRepository.findAll();}

    public void save(User user){
        if(Objects.nonNull(user)) {
            userRepository.save(user);
        }
    }
    public User getById(Long id){
        User user = null;
        if(Objects.nonNull(id)){
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent()){
                user=optionalUser.get();
            }else{
                throw new RuntimeException("User not found with the id " + id);
            }
        }
        return user;
    }
    public User getByEmail(String email){
        User user = null;
        if(Objects.nonNull(email)){
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                user = optionalUser.get();
            }else{
                throw new RuntimeException("User not found with the email " + email);
            }
        }
        return user;
    }
    public User registerUser(String firstName,String lastName,String email,String password){
        if(firstName == null || lastName == null || password == null || email == null){
            return null;
        }else{
            if(userRepository.findByEmail(email).isPresent()){
                System.out.println("User with that email already exist!");
                return null;
            }
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);

            return userRepository.save(user);
        }


    }
    public User authenticate(String email, String password){
        return userRepository.findByEmailAndPassword(email,password).orElse(null);
    }

}
