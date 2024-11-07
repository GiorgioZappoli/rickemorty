package com.marcello.rickemorty;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class JsonController {
    
    @GetMapping("/characters")
    public ResponseEntity<String> getJsonData(@RequestParam(value = "page", defaultValue = "1") int page) throws IOException{
        if (page < 1 || page > 42) {
            return ResponseEntity.badRequest().body("This page does not exists");
        }
        
        String filename = "pages/page" + page + ".json";    
        ClassPathResource jsonResource = new ClassPathResource(filename);

        if (!jsonResource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String jsonData = new String(jsonResource.getInputStream().readAllBytes());
        return ResponseEntity.ok(jsonData);
    }

}