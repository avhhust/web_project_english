package com.springpetproject.repositories;

import com.springpetproject.models.Dictionary;
import com.springpetproject.models.WordTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordTranlsateRepository extends JpaRepository<WordTranslate, Long> {
    @Query(value = "select * from word_translates where word = ?1", nativeQuery = true)
    WordTranslate getByWord(String word);

    @Query(value = "select * from word_translates where translate = ?1", nativeQuery = true)
    WordTranslate getByTranslate(String word);

    @Query(value = "select * from word_translates where dictionary = ?1", nativeQuery = true)
    List<WordTranslate> getAllByDictionary(long dictionaryId);

    @Query(value = "select * from word_translates join dictionaries d on d.id = word_translates.dictionary where owner_id = ?1", nativeQuery = true)
    List<WordTranslate> getAllByDictionaryOwner(long ownerId);

}
