package com.springpetproject.services;

import com.springpetproject.models.Dictionary;

import java.util.List;

public interface DictionaryService {
    Dictionary create(Dictionary dictionary);
    Dictionary readById(long id);
    Dictionary update(Dictionary dictionary);
    void delete(long id);

    Dictionary getByName(String name);
    List<Dictionary> getByUserId(long userId);
}
