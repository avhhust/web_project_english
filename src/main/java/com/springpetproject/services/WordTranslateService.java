package com.springpetproject.services;

import com.springpetproject.models.Dictionary;
import com.springpetproject.models.WordTranslate;

import java.util.List;

public interface WordTranslateService {
    WordTranslate create(WordTranslate wordTranslate);
    WordTranslate update(WordTranslate wordTranslate);
    WordTranslate getByWord(String word);
    WordTranslate getByTranslate(String translate);
    WordTranslate readById(long id);
    void delete(long id);
    
    List<WordTranslate> getAll();
    List<WordTranslate> getAllByDictionary(Dictionary dictionary);
    List<WordTranslate> getAllByUserId(long id);
    List<WordTranslate> getNumberOfByOrderDesc(Dictionary dictionary, long count);
}
