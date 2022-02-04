package com.florian935.mongodb.reactive.polymorphism.controller;

import com.florian935.mongodb.reactive.polymorphism.domain.WeightExercise;
import com.florian935.mongodb.reactive.polymorphism.repository.WeightExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/weight-exercises")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeightExerciseController {

    WeightExerciseRepository weightExerciseRepository;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    Flux<WeightExercise> findAll() {

        return weightExerciseRepository.findAll();
    }
}
