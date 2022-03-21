package net.microservices.Location.repository;

import net.microservices.Location.model.Warehouse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface WarehouseRepository extends ReactiveMongoRepository<Warehouse,String>
{
    Flux<Warehouse> findBywarehouseCodeIgnoreCase(String code);
    Flux<Warehouse> findByStatus(Boolean b);
}
