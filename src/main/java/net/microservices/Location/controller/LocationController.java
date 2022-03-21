package net.microservices.Location.controller;


import net.microservices.Location.dto.OfficeDto;
import net.microservices.Location.dto.WarehouseDto;
import net.microservices.Location.dto.YardDto;
import net.microservices.Location.service.OfficeService;
import net.microservices.Location.service.WarehouseService;
import net.microservices.Location.service.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/location")

public class LocationController
{

    @Autowired
    private OfficeService officeService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private YardService yardService;



//// Office

    @PostMapping("/office")
    public Mono<OfficeDto> addOffice(@RequestBody Mono<OfficeDto> office)
    {
        return officeService.addOffice(office);
    }

    @PutMapping("/office/edit/{id}")
    public Mono<OfficeDto> editOffice(@RequestBody Mono<OfficeDto> office, @PathVariable String id)
    {
        return officeService.editOffice(office,id);
    }

    @PutMapping("/office/savedsearch")
    public Mono<OfficeDto> updateSearchedOffice(@RequestBody Mono<OfficeDto> office)
    {
        return officeService.updateSearchedOffice(office);
    }

    @GetMapping("/office")
    public Flux<OfficeDto> getAllOfficeByStatus()
    {
        return officeService.getAllOfficeByStatus();
    }

    @GetMapping("/office/search")
    public Flux<OfficeDto> getOfficeByDynamicSearch(@RequestParam(required = false) String officeCode,
                                                    @RequestParam(required = false) String officeCountry,
                                                    @RequestParam(required = false) String officeCity,
                                                    @RequestParam(required = false) String officePincode,
                                                    @RequestParam(required = false) String officeCapacity,
                                                    @RequestParam(required = false) String officeTotalArea,
                                                    @RequestParam(required = false) String createdBy,
                                                    @RequestParam(required = false) String createdDate)
    {
        return officeService.getOfficeByDynamicSearch(officeCode,officeCountry,officeCity,officePincode,officeCapacity,officeTotalArea,createdBy,createdDate);
    }

    @GetMapping("/office/savedsearch/codes")
    public Mono<List<String>> getAllOfficeSavedSearchCode()
    {
        return officeService.getAllOfficeSavedSearchCode();
    }

    @GetMapping("/office/saved/{code}")
    public Flux<OfficeDto> getSavedOfficeByCode(@PathVariable String code)
    {
        return officeService.getSavedOfficeByCode(code);
    }


//// Warehouse


    @PostMapping("/warehouse")
    public Mono<WarehouseDto> addWarehouse(@RequestBody Mono<WarehouseDto> warehouse)
    {
        return warehouseService.addWarehouse(warehouse);
    }

    @PutMapping("/warehouse/edit/{id}")
    public Mono<WarehouseDto> editWarehouse(@RequestBody Mono<WarehouseDto> warehouse, @PathVariable String id)
    {
        return warehouseService.editWarehouse(warehouse,id);
    }

    @PutMapping("/warehouse/savedsearch")
    public Mono<WarehouseDto> updateSearchedWarehouse(@RequestBody Mono<WarehouseDto> warehouse)
    {
        return warehouseService.updateSearchedWarehouse(warehouse);
    }

    @GetMapping("/warehouse")
    public Flux<WarehouseDto> getAllWarehouseByStatus()
    {
        return warehouseService.getAllWarehouseByStatus();
    }

    @GetMapping("/warehouse/search")
    public Flux<WarehouseDto> getWarehouseByDynamicSearch(@RequestParam(required = false) String warehouseCode,
                                                          @RequestParam(required = false) String warehouseCountry,
                                                          @RequestParam(required = false) String warehouseCity,
                                                          @RequestParam(required = false) String warehousePincode,
                                                          @RequestParam(required = false) String warehouseCapacity,
                                                          @RequestParam(required = false) String warehouseTotalArea,
                                                          @RequestParam(required = false) String createdBy,
                                                          @RequestParam(required = false) String createdDate)
    {
        return warehouseService.getWarehouseByDynamicSearch(warehouseCode,warehouseCountry,warehouseCity,warehousePincode,warehouseCapacity,warehouseTotalArea,createdBy,createdDate);
    }

    @GetMapping("/warehouse/savedsearch/codes")
    public Mono<List<String>> getAllWarehouseSavedSearchCode()
    {
        return warehouseService.getAllWarehouseSavedSearchCode();
    }

    @GetMapping("/warehouse/saved/{code}")
    public Flux<WarehouseDto> getSavedWarehouseByCode(@PathVariable String code)
    {
        return warehouseService.getSavedWarehouseByCode(code);
    }


//// Yard


    @PostMapping("/yard")
    public Mono<YardDto> addYard(@RequestBody Mono<YardDto> yard)
    {
        return yardService.addYard(yard);
    }

    @PutMapping("/yard/edit/{id}")
    public Mono<YardDto> editYard(@RequestBody Mono<YardDto> yard, @PathVariable String id)
    {
        return yardService.editYard(yard,id);
    }

    @PutMapping("/yard/savedsearch")
    public Mono<YardDto> updateSearchedYard(@RequestBody Mono<YardDto> yard)
    {
        return yardService.updateSearchedYard(yard);
    }

    @GetMapping("/yard")
    public Flux<YardDto> getAllYardByStatus()
    {
        return yardService.getAllYardByStatus();
    }

    @GetMapping("/yard/search")
    public Flux<YardDto> getYardByDynamicSearch(@RequestParam(required = false) String yardCode,
                                                @RequestParam(required = false) String yardCountry,
                                                @RequestParam(required = false) String yardCity,
                                                @RequestParam(required = false) String yardPincode,
                                                @RequestParam(required = false) String yardType,
                                                @RequestParam(required = false) String yardCapacity,
                                                @RequestParam(required = false) String yardTotalArea,
                                                @RequestParam(required = false) String createdBy,
                                                @RequestParam(required = false) String createdDate)
    {
        return yardService.getYardByDynamicSearch(yardCode,yardCountry,yardCity,yardPincode,yardType,yardCapacity,yardTotalArea,createdBy,createdDate);
    }

    @GetMapping("/yard/savedsearch/codes")
    public Mono<List<String>> getAllYardSavedSearchCode()
    {
        return yardService.getAllYardSavedSearchCode();
    }

    @GetMapping("/yard/saved/{code}")
    public Flux<YardDto> getSavedYardByCode(@PathVariable String code)
    {
        return yardService.getSavedYardByCode(code);
    }

}
