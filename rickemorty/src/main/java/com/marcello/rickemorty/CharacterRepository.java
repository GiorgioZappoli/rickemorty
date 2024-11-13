package com.marcello.rickemorty;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, String> {
    // List<Character> findByPage(int page);
}
