package com.marcello.rickemorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
public class JsonController {

    @Autowired
    private CharacterRepository characterRepository;

    private static final int PAGE_SIZE = 20;

    @GetMapping("/characters/page/{page}")
    public ResponseEntity<Map<String, Object>> getPaginationData(@PathVariable int page) {

        if (page < 1) {
            return ResponseEntity.badRequest().body(null);
        }

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);

        Page<Character> charactersPage = characterRepository.findAll(pageRequest);

        long totalElements = characterRepository.count();
        int totalPages = (int) Math.ceil((double) totalElements / PAGE_SIZE);

        Map<String, Object> response = new HashMap<>();
        response.put("results", charactersPage.getContent());
        response.put("totalElements", totalElements);
        response.put("totalPages", totalPages);

        return ResponseEntity.ok(response);
    }
}
