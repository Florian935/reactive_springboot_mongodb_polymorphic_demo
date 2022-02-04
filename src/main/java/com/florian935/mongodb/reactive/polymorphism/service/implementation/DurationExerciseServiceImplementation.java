package com.florian935.mongodb.reactive.polymorphism.service.implementation;

import com.florian935.mongodb.reactive.polymorphism.domain.DurationExercise;
import com.florian935.mongodb.reactive.polymorphism.repository.DurationExerciseRepository;
import com.florian935.mongodb.reactive.polymorphism.service.DurationExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DurationExerciseServiceImplementation implements DurationExerciseService {

    DurationExerciseRepository durationExerciseRepository;

    @Override
    public Mono<Void> deleteAll() {

        return durationExerciseRepository.deleteAll();
    }

    @Override
    public Mono<DurationExercise> findById(String id) {

        return durationExerciseRepository.findById(id);
    }

    @Override
    public Flux<DurationExercise> findAll() {

        return durationExerciseRepository.findAll();
    }

    @Override
    public Mono<Long> count() {

        return durationExerciseRepository.count();
    }
}
