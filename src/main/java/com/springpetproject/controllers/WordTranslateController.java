package com.springpetproject.controllers;

import com.springpetproject.models.WordTranslate;
import com.springpetproject.services.DictionaryService;
import com.springpetproject.services.UserService;
import com.springpetproject.services.WordTranslateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WordTranslateController {
    private final DictionaryService dictionaryService;
    private final WordTranslateService wordTranslateService;
    private final UserService userService;

    public WordTranslateController(DictionaryService dictionaryService, WordTranslateService wordTranslateService, UserService userService) {
        this.dictionaryService = dictionaryService;
        this.wordTranslateService = wordTranslateService;
        this.userService = userService;
    }

    @GetMapping("add_words")
    public String create(Model model){
        model.addAttribute("word_translate", new WordTranslate());
        return "add_words";
    }

    @PostMapping("add_words")
    public String create(@ModelAttribute("word_translate") WordTranslate wordTranslate){
        wordTranslate.setDictionary(dictionaryService.getByName("Simple english"));
        WordTranslate newWordTranslate = wordTranslateService.create(wordTranslate);
        return "redirect:/add_words";
    }


}
