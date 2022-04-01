package com.springpetproject.controllers;

import com.springpetproject.models.Dictionary;
import com.springpetproject.models.User;
import com.springpetproject.models.WordTranslate;
import com.springpetproject.services.DictionaryService;
import com.springpetproject.services.UserService;
import com.springpetproject.services.WordTranslateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final WordTranslateService wordTranslateService;
    private final UserService userService;

    public DictionaryController(DictionaryService dictionaryService, WordTranslateService wordTranslateService, UserService userService) {
        this.dictionaryService = dictionaryService;
        this.wordTranslateService = wordTranslateService;
        this.userService = userService;
    }


    @GetMapping
    public String getAll(Model model){
        Map<Dictionary, List<WordTranslate>> mapOfDictAndWords = new HashMap<>();
        List<Dictionary> dictionaries = dictionaryService.getByUserId(getLoggedUser().getId());
        for(var dictionary : dictionaries){
            if(dictionary.getDateCreation().compareTo(LocalDateTime.now().minusDays(1)) <= -1) ;
            List<WordTranslate> wordTranslateList = wordTranslateService.getNumberOfByOrderDesc(dictionary, 5);
            mapOfDictAndWords.put(dictionary, wordTranslateList);
        }
        model.addAttribute("mapOfDictAndWords", mapOfDictAndWords);
        return "dictionaries_list";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("new_dictionary") Dictionary dictionary){
        dictionary.setOwner(getLoggedUser());
        return "";
    }

    @GetMapping("/{dict_id}")
    public String read(@PathVariable("dict_id") long dictionaryId, Model model){
        Dictionary dictionary = dictionaryService.readById(dictionaryId);
        List<WordTranslate> wordsTranslates = dictionary.getWordTranslates();
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("wordsTranslates", wordsTranslates);
        return "dictionary";
    }



    @GetMapping("/{dict_id}/add_words")
    public String addWords(@PathVariable("dict_id") long dictionaryId, Model model){
        return "";
    }

    @PatchMapping("/{dict_id}/add_words")
    public String addWords(@PathVariable("dict_id") long dictionaryId,
                           @ModelAttribute("wordTranslateList") List<WordTranslate> wordTranslateList){
        return "";
    }


    public String getDateCreation(LocalDateTime date){
        if(date.compareTo(LocalDateTime.now().minusDays(1)) <= -1){
            if(date.getYear() < LocalDateTime.now().getYear()){
                return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            }
            return date.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
        }
        return date.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private User getLoggedUser(){
        return userService.readById(1);
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
