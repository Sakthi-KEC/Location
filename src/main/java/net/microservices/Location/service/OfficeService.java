package net.microservices.Location.service;

import net.microservices.Location.appUtil.OfficeAppUtil;
import net.microservices.Location.dto.OfficeDto;
import net.microservices.Location.exception.CustomException;
import net.microservices.Location.repository.OfficeCustomRepository;
import net.microservices.Location.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OfficeService
{
    @Autowired
    private OfficeRepository officeRepo;

    @Autowired
    private OfficeCustomRepository customRepo;


    // Adding Office to the Repository
    public Mono<OfficeDto> addOffice(Mono<OfficeDto> office)
    {
        return  office.map(OfficeAppUtil::DtoToEntity)
                .flatMap(officeRepo::insert)
                .map(OfficeAppUtil::EntityToDto);
    }

    // Editing Office by updating the old record and inserting the new record
    public Mono<OfficeDto> editOffice(Mono<OfficeDto> office, String id)
    {
        Mono<OfficeDto> r = officeRepo.findById(id).map(OfficeAppUtil::EntityToDto);

        officeRepo.findById(id)
                .flatMap(p -> r.map(OfficeAppUtil::DtoToEntity))
                .doOnNext(e -> e.setStatus(false))
                .flatMap(officeRepo::save)
                .map(OfficeAppUtil::EntityToDto).subscribe();

        return office.map(OfficeAppUtil::DtoToEntity)
                .flatMap(officeRepo::insert)
                .map(OfficeAppUtil::EntityToDto);
    }

    // Updating the Saved Status for the searched Office
    public Mono<OfficeDto> updateSearchedOffice(Mono<OfficeDto> office)
    {
        return  office.map(OfficeAppUtil::DtoToEntity)
                .flatMap(officeRepo::save)
                .map(OfficeAppUtil::EntityToDto);
    }

    // Getting all Office from the Repository
    public Flux<OfficeDto> getAllOfficeByStatus()
    {
        return officeRepo.findByStatus(true).map(OfficeAppUtil::EntityToDto);
    }

    // Getting All Office by dynamically giving any of the fields
    public Flux<OfficeDto> getOfficeByDynamicSearch(String officeCode, String officeCountry, String officeCity, String officePincode, String officeCapacity, String officeTotalArea, String createdBy, String createdDate)
    {
        return customRepo.findProperties(officeCode,officeCountry,officeCity,officePincode,officeCapacity,officeTotalArea,createdBy,createdDate)
                .filter(a->a.getStatus()==true)
                .map(OfficeAppUtil::EntityToDto);
    }

    // Getting all the officeCode of Searched Office
    public Mono<List<String>> getAllOfficeSavedSearchCode()
    {
        return officeRepo.findAll()
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(OfficeAppUtil::EntityToDto)
                .map(a->a.getOfficeCode())
                .distinct()
                .collect(Collectors.toList());
    }

    // Getting all searched Office which saved status is updated
    public Flux<OfficeDto> getSavedOfficeByCode(String code)
    {
        return officeRepo.findByofficeCodeIgnoreCase(code)
                .filter(a->a.getSaved()==true)
                .filter(a->a.getStatus()==true)
                .map(OfficeAppUtil::EntityToDto)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new CustomException("Invalid Office Code"))));
    }
}
