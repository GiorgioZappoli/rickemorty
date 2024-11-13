package com.marcello.rickemorty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Document(collection = "Characters")
@Getter
@Setter
public class Character {
    @Id
    private String id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    @Data
    public class Origin {
        private String name;
        private String url;
    }

    @Data
    public class Location {
        private String name;
        private String url;

    }
}
