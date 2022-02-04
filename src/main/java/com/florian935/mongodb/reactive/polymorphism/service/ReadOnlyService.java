package com.florian935.mongodb.reactive.polymorphism.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReadOnlyService<ENTITY, ID> {

    Mono<ENTITY> findById(ID id);

    Flux<ENTITY> findAll();

    Mono<Long> count();
}
