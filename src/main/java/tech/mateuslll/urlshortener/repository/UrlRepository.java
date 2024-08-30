package tech.mateuslll.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.mateuslll.urlshortener.entities.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
