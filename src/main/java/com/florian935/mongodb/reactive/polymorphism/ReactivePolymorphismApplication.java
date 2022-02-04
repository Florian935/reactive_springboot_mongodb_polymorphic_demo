package com.florian935.mongodb.reactive.polymorphism;

import com.florian935.mongodb.reactive.polymorphism.configuration.InheritanceAwareSimpleReactiveMongoRepository;
import com.florian935.mongodb.reactive.polymorphism.domain.DurationExercise;
import com.florian935.mongodb.reactive.polymorphism.domain.Exercise;
import com.florian935.mongodb.reactive.polymorphism.domain.WeightExercise;
import com.florian935.mongodb.reactive.polymorphism.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@EnableReactiveMongoRepositories(repositoryBaseClass = InheritanceAwareSimpleReactiveMongoRepository.class)
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReactivePolymorphismApplication {

	private final ExerciseRepository exerciseRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReactivePolymorphismApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initData() {

		final List<Exercise> exercises = List.of(
				new Exercise(null, "Bench Press"),
				new WeightExercise(null, "Squat", 100, 10),
				new DurationExercise(null, "Gainage", 120)
		);

		exerciseRepository.deleteAll()
				.thenMany(
						Flux
								.fromIterable(exercises)
								.flatMap(exerciseRepository::save))
				.subscribe(
						System.out::println,
						System.err::println,
						() -> System.out.println("Data initialized !")
				);
	}
}
