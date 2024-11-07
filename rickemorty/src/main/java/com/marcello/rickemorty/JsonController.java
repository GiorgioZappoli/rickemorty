package com.marcello.rickemorty;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class JsonController {
    
    @GetMapping("/characters")
    public ResponseEntity<String> getJsonData() throws IOException{
        ClassPathResource jsonResource = new ClassPathResource("resources.json");
        String jsonData = new String(jsonResource.getInputStream().readAllBytes());
        return ResponseEntity.ok(jsonData);
    }

}