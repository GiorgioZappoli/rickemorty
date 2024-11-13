package com.marcello.rickemorty;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
    Page<Character> findAll(Pageable pageable);
}
