package net.microservices.Location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "warehouse")
public class Warehouse
{
    @Id
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
