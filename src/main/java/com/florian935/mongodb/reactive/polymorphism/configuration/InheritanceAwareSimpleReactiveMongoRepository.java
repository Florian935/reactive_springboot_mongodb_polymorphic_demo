package com.florian935.mongodb.reactive.polymorphism.configuration;

import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.Objects;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class InheritanceAwareSimpleReactiveMongoRepository<T, ID extends Serializable> extends SimpleReactiveMongoRepository<T, ID> {

    MongoEntityInformation<T, ID> mongoEntityInformation;
    ReactiveMongoOperations reactiveMongoOperations;
    Criteria criteria;
    private static final String CLASS_KEY = "_class";
    Query query;

    public InheritanceAwareSimpleReactiveMongoRepository(MongoEntityInformation<T, ID> mongoEntityInformation,
                                                 ReactiveMongoOperations reactiveMongoOperations) {
        super(mongoEntityInformation, reactiveMongoOperations);
        this.reactiveMongoOperations = reactiveMongoOperations;
        this.mongoEntityInformation = mongoEntityInformation;
        this.query = new Query();

        this.criteria = buildCriteria();

        if (Objects.nonNull(criteria)) {
            this.query.addCriteria(criteria);
        }
    }

    private Criteria buildCriteria() {
        final boolean isAnnotationPresent = mongoEntityInformation
                .getJavaType()
                .isAnnotationPresent(TypeAlias.class);

        String documentTypeAliasValue = "";

        if (isAnnotationPresent) {
            documentTypeAliasValue = mongoEntityInformation
                    .getJavaType()
                    .getAnnotation(TypeAlias.class)
                    .value();
        }

        return isAnnotationPresent ? where(CLASS_KEY).is(documentTypeAliasValue) : null;
    }

    @Override
    public Flux<T> findAll() {

        return Objects.nonNull(criteria)
                ? reactiveMongoOperations.find(
                query,
                mongoEntityInformation.getJavaType(),
                mongoEntityInformation.getCollectionName())
                : super.findAll();
    }

    @Override
    public Mono<Void> deleteAll() {

        if (Objects.nonNull(criteria)) {

            return reactiveMongoOperations.remove(
                    query,
                    mongoEntityInformation.getJavaType(),
                    mongoEntityInformation.getCollectionName())
                    .then();
        }

        return super.deleteAll();
    }

    @Override
    public Mono<Long> count() {

        return Objects.nonNull(criteria)
                ? reactiveMongoOperations.count(
                query,
                mongoEntityInformation.getJavaType(),
                mongoEntityInformation.getCollectionName())
                : super.count();
    }
}
