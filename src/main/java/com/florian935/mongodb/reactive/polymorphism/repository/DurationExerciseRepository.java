package com.florian935.mongodb.reactive.polymorphism.repository;

import com.florian935.mongodb.reactive.polymorphism.domain.DurationExercise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationExerciseRepository extends ReactiveMongoRepository<DurationExercise, String> {
}
