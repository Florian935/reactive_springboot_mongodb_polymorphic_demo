package com.florian935.mongodb.reactive.polymorphism.service;

import reactor.core.publisher.Mono;

public interface CrudService<ENTITY, ID> extends ReadOnlyService<ENTITY, ID>  {

    Mono<Void> deleteAll();
}
