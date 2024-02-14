package com.login.quizzmakerapp.Controllers;

import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import com.login.quizzmakerapp.Models.Quiz.Quizz;
import com.login.quizzmakerapp.Models.Quiz.Search;
import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Repositories.QuizRepositories.MakeQuizRepository;
import com.login.quizzmakerapp.Repositories.UserRepositories.UserRepository;
import com.login.quizzmakerapp.Services.Quiz.MakeQuizService;
import com.login.quizzmakerapp.Services.User.UserService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class SearchController {

    @Autowired
    MakeQuizService makeQuizService;
    @Autowired
    UserService userService;

    // Handles the search request
    @PostMapping("/search")
    public String search(@ModelAttribute("id")Long id,@ModelAttribute("search")Search search) {
        // Redirects to the main page if the search query is empty
        if(search.getName().isEmpty()){
            return "redirect:/mainpage/"+id;
        }
        // Redirects to the search results page
        return "redirect:/search/"+id+"/"+search.getName();
    }

    // Retrieves and displays the search results
    @GetMapping("/search/{id}/{search}")
    public String getSearch(@PathVariable(value = "id")Long id,@PathVariable(value = "search")String search, Model model){

        User user1 = new User();
        model.addAttribute("userInfo",user1);
        List<User> list = userService.getAllUsers();
        // Checks if the search query matches any user's email
        for(User user : list){
            if(user.getEmail().equals(search)){
                model.addAttribute("searchUserInfo",userService.getByEmail(search));
                model.addAttribute("switch","on");
            }
        }
        // Retrieves quizzes matching the search query
        model.addAttribute("playerId",id);
        model.addAttribute("quizInfo",makeQuizService.getByEmailOrName(search,search,true));

        Search search1 = new Search();
        model.addAttribute("search",search1);
        model.addAttribute("searchFor",search);

        return "search";
    }

}
