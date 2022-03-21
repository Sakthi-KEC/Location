package net.microservices.Location.repository;

import net.microservices.Location.model.Yard;
import reactor.core.publisher.Flux;


public interface YardCustomRepository
{
    Flux<Yard> findProperties(String yardCode,
                              String yardCountry,
                              String yardCity,
                              String yardPincode,
                              String yardType,
                              String yardCapacity,
                              String yardTotalArea,
                              String createdBy,
                              String createdDate);
}
