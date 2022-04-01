package com.springpetproject.services.implementation;

import com.springpetproject.models.Dictionary;
import com.springpetproject.models.WordTranslate;
import com.springpetproject.repositories.WordTranlsateRepository;
import com.springpetproject.services.WordTranslateService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordTranslateServiceImpl implements WordTranslateService {

    private WordTranlsateRepository wordTranlsateRepository;

    public WordTranslateServiceImpl(WordTranlsateRepository wordTranlsateRepository) {
        this.wordTranlsateRepository = wordTranlsateRepository;
    }

    @Override
    public WordTranslate create(WordTranslate wordTranslate) {
        return wordTranlsateRepository.save(wordTranslate);
    }

    @Override
    public WordTranslate readById(long id) {
        Optional<WordTranslate> wordTranslate = wordTranlsateRepository.findById(id);
        return wordTranslate.get();
    }

    @Override
    public WordTranslate update(WordTranslate wordTranslate) {
        WordTranslate chkWordTranslate = wordTranlsateRepository.getById(wordTranslate.getId());
        return wordTranlsateRepository.save(chkWordTranslate);
    }

    @Override
    public WordTranslate getByWord(String word) {
        Optional<WordTranslate> wordTranslate = Optional.ofNullable(wordTranlsateRepository.getByWord(word));
        return wordTranslate.get();
    }

    @Override
    public WordTranslate getByTranslate(String translate) {
        Optional<WordTranslate> wordTranslate = Optional.ofNullable(wordTranlsateRepository.getByTranslate(translate));
        return wordTranslate.get();
    }

    @Override
    public void delete(long id) {
        WordTranslate wordTranslate = readById(id);
        wordTranlsateRepository.delete(wordTranslate);
    }

    @Override
    public List<WordTranslate> getAll() {
        List<WordTranslate> listOfWordTranslate = wordTranlsateRepository.findAll();
        return listOfWordTranslate.isEmpty() ? new ArrayList<>() : listOfWordTranslate;
    }

    @Override
    public List<WordTranslate> getAllByDictionary(Dictionary dictionary) {
        List<WordTranslate> listOfWordTranslate = wordTranlsateRepository.getAllByDictionary(dictionary.getId());
        return listOfWordTranslate.isEmpty() ? new ArrayList<>() : listOfWordTranslate;
    }

    @Override
    public List<WordTranslate> getAllByUserId(long id) {
        List<WordTranslate> listOfWordTranslate = wordTranlsateRepository.getAllByDictionaryOwner(id);
        return listOfWordTranslate.isEmpty() ? new ArrayList<>() : listOfWordTranslate;
    }

    @Override
    public List<WordTranslate> getNumberOfByOrderDesc(Dictionary dictionary, long count) {
        List<WordTranslate> listOfWordTranslate = getAllByDictionary(dictionary);
        Collections.reverse(listOfWordTranslate);
        return listOfWordTranslate.stream()
                .limit(count)
                .toList();
    }
}
