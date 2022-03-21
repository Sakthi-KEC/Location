package net.microservices.Location.service;

import net.microservices.Location.appUtil.WarehouseAppUtil;
import net.microservices.Location.dto.WarehouseDto;
import net.microservices.Location.exception.CustomException;
import net.microservices.Location.repository.WarehouseCustomRepository;
import net.microservices.Location.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WarehouseService
{
    @Autowired
    private WarehouseRepository warehouseRepo;

    @Autowired
    private WarehouseCustomRepository customRepo;


    // Adding Warehouse to the Repository
    public Mono<WarehouseDto> addWarehouse(Mono<WarehouseDto> warehouse)
    {
        return  warehouse.map(WarehouseAppUtil::DtoToEntity)
                .flatMap(warehouseRepo::insert)
                .map(WarehouseAppUtil::EntityToDto);
    }

    // Editing Warehouse by updating the old record and inserting the new record
    public Mono<WarehouseDto> editWarehouse(Mono<WarehouseDto> warehouse, String id)
    {
        Mono<WarehouseDto> r = warehouseRepo.findById(id).map(WarehouseAppUtil::EntityToDto);

        warehouseRepo.findById(id)
                .flatMap(p -> r.map(WarehouseAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(warehouseRepo::save)
                .map(WarehouseAppUtil::EntityToDto).subscribe();

        return warehouse.map(WarehouseAppUtil::DtoToEntity)
                .flatMap(warehouseRepo::insert)
                .map(WarehouseAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Warehouse
    public Mono<WarehouseDto> updateSearchedWarehouse(Mono<WarehouseDto> warehouse)
    {
        return  warehouse.map(WarehouseAppUtil::DtoToEntity)
                .flatMap(warehouseRepo::save)
                .map(WarehouseAppUtil::EntityToDto);
    }

    // Getting all Warehouse from the Repository
    public Flux<WarehouseDto> getAllWarehouseByStatus()
    {
        return warehouseRepo.findByStatus(true).map(WarehouseAppUtil::EntityToDto);
    }

    // Getting All Warehouse by dynamically giving any of the fields
    public Flux<WarehouseDto> getWarehouseByDynamicSearch(String warehouseCode, String warehouseCountry, String warehouseCity, String warehousePincode, String warehouseCapacity, String warehouseTotalArea, String createdBy, String createdDate)
    {
        return customRepo.findProperties(warehouseCode,warehouseCountry,warehouseCity,warehousePincode,warehouseCapacity,warehouseTotalArea,createdBy,createdDate)
                .filter(a->a.getStatus()==true)
                .map(WarehouseAppUtil::EntityToDto);
    }

    // Getting all the warehouseCode of Searched Warehouse
    public Mono<List<String>> getAllWarehouseSavedSearchCode()
    {
        return warehouseRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(WarehouseAppUtil::EntityToDto)
                .map(a->a.getWarehouseCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Warehouse which saved status is updated
    public Flux<WarehouseDto> getSavedWarehouseByCode(String code)
    {
        return warehouseRepo.findBywarehouseCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(WarehouseAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Warehouse Code"))));
    }

}
