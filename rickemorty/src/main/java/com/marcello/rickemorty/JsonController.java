package com.marcello.rickemorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class JsonController {

    @Autowired
    private CharacterRepository characterRepository;
    
    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getJsonData(@RequestParam(value = "page", defaultValue = "1") int page){
        if (page < 1 || page > 42) {
            return ResponseEntity.badRequest().body(null);
        }
        
        List<Character> characters = characterRepository.findAll();
        return characters.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(characters);
    }

}