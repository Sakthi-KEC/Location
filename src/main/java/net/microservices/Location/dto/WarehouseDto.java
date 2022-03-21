package net.microservices.Location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto
{
    private String id;
    private String warehouseCode;
    private String warehouseCountry;
    private String warehouseCity;
    private String warehousePincode;
    private String warehouseCapacity;
    private String warehouseTotalArea;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}
