package com.springpetproject.repositories;

import com.springpetproject.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
    @Query(value = "select * from dictionaries where name = ?1", nativeQuery = true)
    Dictionary getByName(String name);

    @Query(value = "select * from dictionaries where ownerId = ?1", nativeQuery = true)
    List<Dictionary> getByOwnerId(long ownerId);
}
