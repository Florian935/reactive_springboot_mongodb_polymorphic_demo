package com.florian935.mongodb.reactive.polymorphism.repository;

import com.florian935.mongodb.reactive.polymorphism.domain.Exercise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends ReactiveMongoRepository<Exercise, String> {
}
