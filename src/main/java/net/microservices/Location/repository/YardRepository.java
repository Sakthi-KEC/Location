package net.microservices.Location.repository;

import net.microservices.Location.model.Yard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface YardRepository extends ReactiveMongoRepository<Yard,String>
{

    Flux<Yard> findByyardCodeIgnoreCase(String code);
    Flux<Yard> findByStatus(Boolean b);
}
