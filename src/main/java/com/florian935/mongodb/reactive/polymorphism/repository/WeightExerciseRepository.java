package com.florian935.mongodb.reactive.polymorphism.repository;

import com.florian935.mongodb.reactive.polymorphism.domain.WeightExercise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightExerciseRepository extends ReactiveMongoRepository<WeightExercise, String> {
}
