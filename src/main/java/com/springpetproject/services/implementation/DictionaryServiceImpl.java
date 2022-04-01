package com.springpetproject.services.implementation;

import com.springpetproject.models.Dictionary;
import com.springpetproject.repositories.DictionaryRepository;
import com.springpetproject.services.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryRepository dictionaryRepository;

    public DictionaryServiceImpl(DictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public Dictionary create(Dictionary dictionary) {
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public Dictionary readById(long id) {
        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        return dictionary.get();
    }

    @Override
    public Dictionary update(Dictionary dictionary) {
        Dictionary dict = readById(dictionary.getId());
        return dictionaryRepository.save(dict);
    }

    @Override
    public void delete(long id) {
        Dictionary dictionary = readById(id);
        dictionaryRepository.delete(dictionary);
    }

    @Override
    public List<Dictionary> getByUserId(long userId) {
        List<Dictionary> dictionaries = dictionaryRepository.findAll();
        return dictionaries.isEmpty() ? new ArrayList<>() : dictionaries;
    }

    @Override
    public Dictionary getByName(String name) {
        Optional<Dictionary> dictionary = Optional.ofNullable(dictionaryRepository.getByName(name));
        return dictionary.get();
    }
}
