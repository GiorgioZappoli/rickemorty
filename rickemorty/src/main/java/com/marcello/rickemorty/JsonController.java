package com.marcello.rickemorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@RestController
@CrossOrigin(origins = "*")
public class JsonController {

    @Autowired
    private CharacterRepository characterRepository;

    private static final int PAGE_SIZE = 20;

    @GetMapping("/characters")
    public ResponseEntity<CharactersResponse> getPaginationData(
            @RequestParam(value = "page", defaultValue = "1") int page) {

        if (page < 1 || page > 42) {
            return ResponseEntity.badRequest().body(null);
        }

        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Direction.ASC, "id"));
        Page<Character> charactersPage = characterRepository.findAll(pageRequest);

        CharactersResponse response = new CharactersResponse();

        CharactersResponse.Info info = new CharactersResponse.Info();
        info.setCount((int) characterRepository.count());
        info.setPages((int) Math.ceil((double) info.getCount() / PAGE_SIZE));
        info.setNext(page < info.getPages() ? "http://localhost:9000/characters?page=" + (page + 1) : null);
        info.setPrev(page > 1 ? "http://localhost:9000/characters?page=" + (page - 1) : null);

        response.setInfo(info);
        response.setResults(charactersPage.getContent());

        return ResponseEntity.ok(response);
    }
}
