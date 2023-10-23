package gr.ihu.dw.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JWkdataRepository extends MongoRepository<JWKdata, String> {
    Optional<JWKdata> findFirstByOrderByTimestampDesc();
}
