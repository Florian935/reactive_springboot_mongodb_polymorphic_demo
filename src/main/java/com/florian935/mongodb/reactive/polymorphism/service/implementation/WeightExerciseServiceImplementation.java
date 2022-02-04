package com.florian935.mongodb.reactive.polymorphism.service.implementation;

import com.florian935.mongodb.reactive.polymorphism.domain.WeightExercise;
import com.florian935.mongodb.reactive.polymorphism.repository.WeightExerciseRepository;
import com.florian935.mongodb.reactive.polymorphism.service.WeightExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class WeightExerciseServiceImplementation implements WeightExerciseService {

    WeightExerciseRepository weightExerciseRepository;

    @Override
    public Mono<Void> deleteAll() {

        return weightExerciseRepository.deleteAll();
    }

    @Override
    public Mono<WeightExercise> findById(String id) {

        return weightExerciseRepository.findById(id);
    }

    @Override
    public Flux<WeightExercise> findAll() {

        return weightExerciseRepository.findAll();
    }

    @Override
    public Mono<Long> count() {

        return weightExerciseRepository.count();
    }
}
