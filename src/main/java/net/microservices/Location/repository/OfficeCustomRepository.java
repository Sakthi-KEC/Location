package net.microservices.Location.repository;

import net.microservices.Location.model.Office;
import reactor.core.publisher.Flux;


public interface OfficeCustomRepository
{
    Flux<Office> findProperties(String officeCode,
                                String officeCountry,
                                String officeCity,
                                String officePincode,
                                String officeCapacity,
                                String officeTotalArea,
                                String createdBy,
                                String createdDate);
}
