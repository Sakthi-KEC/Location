package net.microservices.Location.repository;

import net.microservices.Location.model.Warehouse;
import reactor.core.publisher.Flux;


public interface WarehouseCustomRepository
{
    Flux<Warehouse> findProperties(String warehouseCode,
                                   String warehouseCountry,
                                   String warehouseCity,
                                   String warehousePincode,
                                   String warehouseCapacity,
                                   String warehouseTotalArea,
                                   String createdBy,
                                   String createdDate);
}

