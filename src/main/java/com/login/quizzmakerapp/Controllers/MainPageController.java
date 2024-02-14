package com.login.quizzmakerapp.Controllers;

import com.login.quizzmakerapp.Models.Quiz.QuizStats;
import com.login.quizzmakerapp.Models.Quiz.Quizz;
import com.login.quizzmakerapp.Models.Quiz.Search;
import com.login.quizzmakerapp.Services.Quiz.MakeQuizService;
import com.login.quizzmakerapp.Services.Quiz.QuizStatsService;
import com.login.quizzmakerapp.Services.User.UserService;
import com.login.quizzmakerapp.Services.User.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    UserService userService;
    @Autowired
    MakeQuizService makeQuizService;
    @Autowired
    UserStatsService userStatsService;
    @Autowired
    QuizStatsService quizStatsService;

    // Endpoint to handle the creation of a new quiz
    @GetMapping("/makeQuiz/{id}")
    public String makeQuiz(@PathVariable(value = "id")long id,Model model){
        model.addAttribute("userInfo",userService.getById(id));
        model.addAttribute("quizInfo",new Quizz());

        Search search = new Search();
        model.addAttribute("search",search);

        return "quizzMaker";
    }

    // Endpoint to list all quizzes created by a user
    @GetMapping("/listQuiz/{id}")
    public String listQuiz(@PathVariable(value = "id")long id, Model model){

        List<Quizz> list = makeQuizService.listOfQuizzes(userService.getById(id).getEmail());

        model.addAttribute("quizInfo",list);
        model.addAttribute("userInfo",userService.getById(id));

        Search search = new Search();
        model.addAttribute("search",search);

        return "quizList";
    }

    // Endpoint to display user statistics
    @GetMapping("/userStats/{id}")
    public String UserStats(@PathVariable(value = "id")long id,Model model){

        model.addAttribute("userStatsInfo",userStatsService.getByPlayerId(id));
        model.addAttribute("userInfo",userService.getById(id));

        List<QuizStats> quizStatsList = quizStatsService.listOfPlayerQuizes(id);

        model.addAttribute("quizStatsList",quizStatsList);

        Search search = new Search();
        model.addAttribute("search",search);

        List<Quizz> quizList = makeQuizService.getByType(true);
        model.addAttribute("quizList",quizList);

        return "userStats";
    }

    // Endpoint to display statistics of another user
    @GetMapping("/otherUserStats/{id}")
    public String OtherUserStats(@PathVariable(value = "id")long id, @RequestParam(value = "userInfo", required = false) Long playerId, Model model){

        model.addAttribute("userStatsInfo",userStatsService.getByPlayerId(id));
        model.addAttribute("searchInfo",userService.getById(id));

        List<QuizStats> quizStatsList = quizStatsService.listOfPlayerQuizes(id);

        model.addAttribute("quizStatsList",quizStatsList);

        model.addAttribute("userInfoId",playerId);

        Search search = new Search();
        model.addAttribute("search",search);

        List<Quizz> quizList = makeQuizService.getByType(true);
        model.addAttribute("quizList",quizList);

        return "searchUserStats";
    }

    // Endpoint to search for quizzes
    @GetMapping("/searchQuiz")
    public String Search(){
        return "search";
    }

    // Endpoint to display the main page
    @GetMapping("/mainpage/{id}")
    public String mainPage(@PathVariable(value = "id")long id,Model model){
        model.addAttribute("userInfo",userService.getById(id));

        Search search = new Search();
        model.addAttribute("search",search);

        List<Quizz> quizList = makeQuizService.getByType(true);
        model.addAttribute("quizList",quizList);

        return "feedPage";
    }

}
