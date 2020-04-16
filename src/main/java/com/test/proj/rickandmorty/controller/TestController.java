package com.test.proj.rickandmorty.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.proj.rickandmorty.util.JsonParseToDb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JsonParseToDb jsonParser;

    public TestController(JsonParseToDb jsonParser) {
        this.jsonParser = jsonParser;
    }

    @GetMapping
    public void readJson() throws JsonProcessingException {
    }
}
