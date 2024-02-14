package com.login.quizzmakerapp.Controllers;

import com.login.quizzmakerapp.Models.Quiz.Quizz;
import com.login.quizzmakerapp.Models.Quiz.Search;
import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Services.Quiz.MakeQuizService;
import com.login.quizzmakerapp.Services.User.UserService;
import com.login.quizzmakerapp.Services.User.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginRegisterController {

    final
    UserService userService;

    // Constructor injection of UserService
    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    UserStatsService userStatsService;
    @Autowired
    MakeQuizService makeQuizService;

    // Mapping for the home page
    @GetMapping("/")
    public String index(){
        return "mainPage";
    }

    // Mapping to display the login page
    @GetMapping("/login")
    public String getLoginPage(Model model){
        // Adding empty User objects for login and registration forms
        model.addAttribute("loginRequest",new User());
        model.addAttribute("registerRequest",new User());
        return "loginPage";
    }

    // Mapping for user registration
    @PostMapping("/register")
    public String register(@ModelAttribute User user,Model model){
        try {
            // Handling registration process and checking for existing users
            if (userService.getByEmail(user.getEmail()) != null) {
                model.addAttribute("error", "notNull");
                return "loginPage";
            } else {
                User registeredUser = userService.registerUser(user.getFirstName(), user.getLastName(),user.getEmail(),user.getPassword());
                userStatsService.setUserStats(registeredUser);
                model.addAttribute("userInfo", registeredUser);
                return "redirect:/feedPage/"+registeredUser.getId();
            }
        }catch (Exception e){
            User registeredUser = userService.registerUser(user.getFirstName(), user.getLastName(),user.getEmail(),user.getPassword());
            userStatsService.setUserStats(registeredUser);
            model.addAttribute("userInfo", registeredUser);
            return "redirect:/feedPage/"+registeredUser.getId();
        }
    }

    // Mapping for user login
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        // Authenticating user credentials
        User authenticated = userService.authenticate(user.getEmail(),user.getPassword());
        if(authenticated != null){
            return "redirect:/feedPage/"+authenticated.getId();
        }else{
            model.addAttribute("error2","WrongEmailOrPassword");
            return "loginPage";
        }
    }

    // Mapping to display the feed page
    @GetMapping("/feedPage/{id}")
    public String feedPage(@PathVariable(value = "id")long id,Model model){
        // Fetching user information and available quizzes for the feed page
        model.addAttribute("userInfo",userService.getById(id));
        Search search = new Search();
        model.addAttribute("search",search);
        List<Quizz> quizList = makeQuizService.getByType(true);
        model.addAttribute("quizList",quizList);
        return "feedPage";
    }
}
