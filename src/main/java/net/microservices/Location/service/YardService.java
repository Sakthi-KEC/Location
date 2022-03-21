package net.microservices.Location.service;

import net.microservices.Location.appUtil.YardAppUtil;
import net.microservices.Location.dto.YardDto;
import net.microservices.Location.exception.CustomException;
import net.microservices.Location.repository.YardCustomRepository;
import net.microservices.Location.repository.YardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class YardService
{
    @Autowired
    private YardRepository yardRepo;

    @Autowired
    private YardCustomRepository customRepo;


    // Adding Yard to the Repository
    public Mono<YardDto> addYard(Mono<YardDto> yard)
    {
        return  yard.map(YardAppUtil::DtoToEntity)
                .flatMap(yardRepo::insert)
                .map(YardAppUtil::EntityToDto);
    }

    // Editing Yard by updating the old record and inserting the new record
    public Mono<YardDto> editYard(Mono<YardDto> yard, String id)
    {
        Mono<YardDto> r = yardRepo.findById(id).map(YardAppUtil::EntityToDto);

        yardRepo.findById(id)
                .flatMap(p -> r.map(YardAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(yardRepo::save)
                .map(YardAppUtil::EntityToDto).subscribe();

        return yard.map(YardAppUtil::DtoToEntity)
                .flatMap(yardRepo::insert)
                .map(YardAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Yard
    public Mono<YardDto> updateSearchedYard(Mono<YardDto> yard)
    {
        return  yard.map(YardAppUtil::DtoToEntity)
                .flatMap(yardRepo::save)
                .map(YardAppUtil::EntityToDto);
    }

    // Getting all Yard from the Repository
    public Flux<YardDto> getAllYardByStatus()
    {
        return yardRepo.findByStatus(true).map(YardAppUtil::EntityToDto);
    }

    // Getting All Yard by dynamically giving any of the fields
    public Flux<YardDto> getYardByDynamicSearch(String yardCode, String yardCountry, String yardCity, String yardPincode, String yardType, String yardCapacity, String yardTotalArea, String createdBy, String createdDate)
    {
        return customRepo.findProperties(yardCode,yardCountry,yardCity,yardPincode,yardType,yardCapacity,yardTotalArea,createdBy,createdDate)
                .filter(a->a.getStatus()==true)
                .map(YardAppUtil::EntityToDto);
    }

    // Getting all the yardCode of Searched Yard
    public Mono<List<String>> getAllYardSavedSearchCode()
    {
        return yardRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(YardAppUtil::EntityToDto)
                .map(a->a.getYardCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Yard which saved status is updated
    public Flux<YardDto> getSavedYardByCode(String code)
    {
        return yardRepo.findByyardCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(YardAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Yard Code"))));
    }
}
