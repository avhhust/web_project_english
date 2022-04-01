package com.springpetproject.controllers;

import com.springpetproject.models.User;
import com.springpetproject.models.WordTranslate;
import com.springpetproject.repositories.WordTranlsateRepository;
import com.springpetproject.services.DictionaryService;
import com.springpetproject.services.UserService;
import com.springpetproject.services.WordTranslateService;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/studying")
public class StudyController {

    private final DictionaryService dictionaryService;
    private final WordTranslateService wordTranslateService;
    private final UserService userService;

    private List<WordTranslate> wordTranslateList;

    public StudyController(DictionaryService dictionaryService, WordTranslateService wordTranslateService, WordTranlsateRepository wordTranlsateRepository, WordTranslateService wordTranslateService1, UserService userService) {
        this.dictionaryService = dictionaryService;
        this.wordTranslateService = wordTranslateService1;
        this.userService = userService;
    }

    @GetMapping("/flashcards")
    public String studyFromCards(@RequestParam(name = "ind", required = false) Optional<Integer> index,
                                 Model model){
        int ind = index.orElseGet(() -> {initial(); return 0;});
        if(wordTranslateList.isEmpty()){
            return "redirect:/home";
        }

        try{
            WordTranslate wordTranslate = wordTranslateList.get(ind);
            model.addAttribute("wordTranslate", wordTranslate);
            model.addAttribute("index", ind);
            return "flashcards";
        } catch (IndexOutOfBoundsException e){
            return "redirect:/home";
        }
    }

//    @GetMapping("/cards/{word_id}")
//    public String getNextCard(@PathVariable("word_id") long wordId, Model model){
//        WordTranslate wordTranslate = wordTranslateService.readById(wordId);
//        model.addAttribute("wordId", wordId);
//        model.addAttribute("wordTranslate", wordTranslate);
//        return "cards";
//    }

    private User getLoggedUser(){
        return userService.readById(1);
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void initial(){
        wordTranslateList = wordTranslateService.getAllByUserId(getLoggedUser().getId());
    }
}
