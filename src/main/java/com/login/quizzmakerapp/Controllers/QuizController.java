package com.login.quizzmakerapp.Controllers;

import com.login.quizzmakerapp.Models.Quiz.*;
import com.login.quizzmakerapp.Models.User.User;
import com.login.quizzmakerapp.Models.User.UserStats;
import com.login.quizzmakerapp.Services.Quiz.AnswerService;
import com.login.quizzmakerapp.Services.Quiz.MakeQuizService;
import com.login.quizzmakerapp.Services.Quiz.QuizQuestionsService;
import com.login.quizzmakerapp.Services.Quiz.QuizStatsService;
import com.login.quizzmakerapp.Services.User.UserService;
import com.login.quizzmakerapp.Services.User.UserStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StopWatch;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class QuizController {

    @Autowired
    UserService userService;
    @Autowired
    MakeQuizService makeQuizService;
    @Autowired
    QuizQuestionsService quizQuestionsService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserStatsService userStatsService;
    @Autowired
    QuizStatsService quizStatsService;

    boolean pr = false;
    @PostMapping("/makeQuiz")
    public String makeQuiz(@ModelAttribute("quizInfo") Quizz quizz, RedirectAttributes redirectAttributes){
        makeQuizService.save(quizz);
        redirectAttributes.addFlashAttribute("quizInfo",quizz);
        pr = false;
        return"redirect:/setQuizQuestions";
    }
    long counter = 0;
    boolean check = false;
    long bcounter=0;

    @GetMapping("/setQuizQuestions")
    public String setQuizQuestions(@ModelAttribute("quizInfo")Quizz quizz,Model model,@RequestParam(value = "error", required = false) String error,RedirectAttributes redirectAttributes){
        check = false;
        model.addAttribute("quizInfo", quizz);
        model.addAttribute("questionInfo", new QuizQuestions());
        counter++;
        if(counter>=quizz.getNumberOfQuestions()){check = true;bcounter = counter;counter = 0;}

        return "quizMakeQuestion";

    }

    @PostMapping("/quizSaveQuestion")
    public String saveQuestion(@ModelAttribute("questionInfo")QuizQuestions quizQuestions,RedirectAttributes redirectAttributes,Model model){
        if (quizQuestions.getCorectAnswer().equals(quizQuestions.getAnswerOne()) || quizQuestions.getCorectAnswer().equals(quizQuestions.getAnswerTwo()) || quizQuestions.getCorectAnswer().equals(quizQuestions.getAnswerThree()) || quizQuestions.getCorectAnswer().equals(quizQuestions.getAnswerFour())) {
            if(!pr) {
                quizQuestionsService.save(quizQuestions);
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Correct Answer Don't Match Any Answer");
            redirectAttributes.addAttribute("quizInfo", makeQuizService.getById(quizQuestions.getQuizTableid()));
            if(check){counter=bcounter;}
            counter--;
            return "redirect:/setQuizQuestions";
        }
        Quizz quizz = makeQuizService.getById(quizQuestions.getQuizTableid());
        User user = userService.getById((long)quizz.getOwnerId());
        if(check){
            pr=true;
            return "redirect:/mainpage/"+user.getId();
        }
        redirectAttributes.addAttribute("quizInfo", makeQuizService.getById(quizQuestions.getQuizTableid()));
        return "redirect:/setQuizQuestions";
    }

    int counter2 = 0;
    private static int playId = 0;
    int i = 0;
    Instant start;
    Instant end;
    @GetMapping("/play/{id}")
    public String statsQuiz(@PathVariable(value = "id")long id,Model model,@RequestParam(value = "playerId", required = false) Long playerId,RedirectAttributes redirectAttributes){

        List<QuizStats> quizStatsList = quizStatsService.listOfQuizStats(id);

        List<QuizStats> filteredList = quizStatsList.stream()
                .filter(quizStats -> quizStats.getCorrectAnswers() == makeQuizService.getById(id).getNumberOfQuestions())
                .filter(quizStats -> quizStats.getTimePlayed() == 1)
                .collect(Collectors.toList());

        // Sort the filtered list by time
        Collections.sort(filteredList, Comparator.comparingDouble(QuizStats::getTime));
        filteredList = filteredList.subList(0, Math.min(10, filteredList.size()));
        filteredList.forEach(quizStats -> quizStats.setTime(Math.round(quizStats.getTime() * 100.0) / 100.0));


        model.addAttribute("quizList",filteredList);
        redirectAttributes.addAttribute("id",id);
        model.addAttribute("playerId",playerId);

        model.addAttribute("userInfo",userService.getById(playerId));

        Search search = new Search();
        model.addAttribute("search",search);

        return "quizStats";
    }

    @GetMapping("/playQuiz/{id}")
    public String playQuiz(@PathVariable(value = "id") long id, Model model,@RequestParam(value = "playerId", required = false) Long playerId,RedirectAttributes redirectAttributes) {
        int numberOfQuestions = makeQuizService.getById(id).getNumberOfQuestions();
        if(i==0){
            try {
                List<QuizStats> quizStatsList = quizStatsService.listOfQuizStatsAdd(id,playerId);

                QuizStats quizStats1 = new QuizStats();
                quizStats1.setTimePlayed(quizStatsList.getLast().getTimePlayed()+1);
                quizStats1.setPlayerEmail(userService.getById(playerId).getEmail());
                quizStats1.setPlayerId(userService.getById(playerId).getId());
                quizStats1.setQuizName(makeQuizService.getById(id).getName());
                quizStats1.setQuizId(id);
                quizStatsService.save(quizStats1);
            }catch (Exception e){
                QuizStats quizStats = new QuizStats();
                quizStats.setPlayerEmail(userService.getById(playerId).getEmail());
                quizStats.setPlayerId(userService.getById(playerId).getId());
                quizStats.setQuizId(id);
                quizStats.setTimePlayed(1);
                quizStats.setQuizName(makeQuizService.getById(id).getName());
                quizStatsService.save(quizStats);
            }
            i++;
        }
        if (counter2 < numberOfQuestions) {
            QuizQuestions currentQuestion = quizQuestionsService.listOfQuestions(id).get(counter2);

            model.addAttribute("quizInfo", makeQuizService.getById(id));
            model.addAttribute("quizQuestionInfo", quizQuestionsService.listOfQuestions(id));
            model.addAttribute("questionsInfo", currentQuestion);
            model.addAttribute("odg", 0);
            model.addAttribute("player",userService.getById(playerId));
            start = Instant.now();
            counter2++;
            return "quizPlay";
        }
        redirectAttributes.addAttribute("counter",counter2);
        counter2=0;
        playId++;
        i = 0;
        return "redirect:/answerList/"+userService.getById(playerId).getId();
    }

    @PostMapping("/submitAnswer")
    public String submitAnswer(@RequestParam("odg") int odg,Model model,@RequestParam("QuizId") long id,@RequestParam("player") long playerId,RedirectAttributes redirectAttributes){
        QuizQuestions quizQuestions1 = quizQuestionsService.getById(id);

        if(odg == 0){
            redirectAttributes.addAttribute("playerId",playerId);
            redirectAttributes.addFlashAttribute("error", "Chose Answer");
            redirectAttributes.addAttribute("playerId",playerId);
            counter2--;
            return "redirect:/playQuiz/"+quizQuestionsService.getById(id).getQuizTableid();
        }

        Answers answers = new Answers();
        answers.setPlayId(playId);
        answers.setQuizId(makeQuizService.getById(quizQuestions1.getQuizTableid()).getId());
        answers.setPlayerId(playerId);
        answers.setQuizQuestionId(quizQuestions1.getId());
        answers.setCorrectAnswer(quizQuestions1.getCorectAnswer());
        answers.setQuestion(quizQuestions1.getQuestion());

        switch (odg){
            case 1:
                answers.setPlayerAnswer(quizQuestions1.getAnswerOne());
                break;
            case 2:
                answers.setPlayerAnswer(quizQuestions1.getAnswerTwo());
                break;
            case 3:
                answers.setPlayerAnswer(quizQuestions1.getAnswerThree());
                break;
            case 4:
                answers.setPlayerAnswer(quizQuestions1.getAnswerFour());
                break;
        }
        List<QuizStats> quizStatsList = quizStatsService.listOfQuizStats(makeQuizService.getById(quizQuestions1.getQuizTableid()).getId());
        if(answers.getPlayerAnswer().equals(quizQuestions1.getCorectAnswer())){
            userStatsService.getByPlayerId(playerId).setCorrect(userStatsService.getByPlayerId(playerId).getCorrect()+1);
            quizStatsList.getLast().setCorrectAnswers(quizStatsList.getLast().getCorrectAnswers()+1);
        }else{
            userStatsService.getByPlayerId(playerId).setWrong(userStatsService.getByPlayerId(playerId).getWrong()+1);
            quizStatsList.getLast().setWrongAnswers(quizStatsList.getLast().getWrongAnswers()+1);
        }
        end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        double time = timeElapsed.toMillis();
        time /= 1000;
        if(counter2 == 1){
            quizStatsList.getLast().setTime(time);
        }else {
            quizStatsList.getLast().setTime(((quizStatsList.getLast().getTime() + time) / (2)));
        }
        answerService.save(answers);
        redirectAttributes.addAttribute("playerId",playerId);
        return "redirect:/playQuiz/"+quizQuestions1.getQuizTableid();
    }

    @GetMapping("/answerList/{id}")
    public String answerList(@PathVariable(value = "id") long id, Model model, @RequestParam("counter") int counter) {

        List<Answers> answerList = answerService.getAllAnswers();
        List<Answers> subList;

        UserStats userStats = userStatsService.getByPlayerId(id);
        userStats.setNumberOfQuizPlayed(userStats.getNumberOfQuizPlayed() + 1);
        float c = userStats.getCorrect();
        float w = userStats.getWrong();
        userStats.setCWRatio((c/(c+w))*100);
        userStatsService.save(userStats);

        if (counter > 0 && counter <= answerList.size()) {
            subList = answerList.subList(answerList.size() - counter, answerList.size());
        } else {

            subList = Collections.emptyList();
        }
        model.addAttribute("answersList", subList);
        model.addAttribute("userInfo", userService.getById(id));
        Search search = new Search();
        model.addAttribute("search",search);
        model.addAttribute("time",quizStatsService.listOfPlayerQuizes(id).getFirst().getTime());
        return "answerList";

    }

    @GetMapping("/makePublic/{id}")
    public String changePublic(@PathVariable(value = "id")long id){

        if(makeQuizService.getById(id).isType()){
            makeQuizService.getById(id).setType(false);
            System.out.println("TRUUUUU");
        }else{
            makeQuizService.getById(id).setType(true);
        }
        makeQuizService.save(makeQuizService.getById(id));

        return "redirect:/listQuiz/"+makeQuizService.getById(id).getOwnerId();
    }
}