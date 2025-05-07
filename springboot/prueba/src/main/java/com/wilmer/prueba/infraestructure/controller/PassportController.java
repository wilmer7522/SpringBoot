package com.wilmer.prueba.infraestructure.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassportController {

   /* private final PassportService passportService;

    public PassportController(PassportService passportService){
    this.passportService = passportService;
    }

    @GetMapping("/passport")
    public List<Passport> findAll(
        @RequestParam(name = "filter", defaultValue = "") String filter,
        @RequestParam(name = "value", defaultValue = "") String value
    ){

      //  List<Passport> results = passportService.findAllByFilter(filter, value);

        return results;
    }*/
}
