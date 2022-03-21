package net.microservices.Location.repository;

import net.microservices.Location.model.Office;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface OfficeRepository extends ReactiveMongoRepository<Office,String>
{
    Flux<Office> findByofficeCodeIgnoreCase(String code);
    Flux<Office> findByStatus(Boolean b);
}
