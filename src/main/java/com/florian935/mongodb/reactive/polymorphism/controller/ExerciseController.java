package com.florian935.mongodb.reactive.polymorphism.controller;

import com.florian935.mongodb.reactive.polymorphism.domain.Exercise;
import com.florian935.mongodb.reactive.polymorphism.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/exercises")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExerciseController {

    ExerciseRepository exerciseRepository;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    Flux<Exercise> findAll() {

        return exerciseRepository.findAll();
    }
}
