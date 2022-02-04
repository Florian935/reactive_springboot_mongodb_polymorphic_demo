package com.florian935.mongodb.reactive.polymorphism.service.implementation;

import com.florian935.mongodb.reactive.polymorphism.domain.Exercise;
import com.florian935.mongodb.reactive.polymorphism.repository.ExerciseRepository;
import com.florian935.mongodb.reactive.polymorphism.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExerciseServiceImplementation implements ExerciseService {

    ExerciseRepository exerciseRepository;

    @Override
    public Mono<Void> deleteAll() {

        return exerciseRepository.deleteAll();
    }

    @Override
    public Mono<Exercise> findById(String id) {

        return exerciseRepository.findById(id);
    }

    @Override
    public Flux<Exercise> findAll() {

        return exerciseRepository.findAll();
    }

    @Override
    public Mono<Long> count() {

        return exerciseRepository.count();
    }
}
