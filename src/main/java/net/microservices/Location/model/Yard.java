package net.microservices.Location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "yard")
public class Yard
{
    @Id
    private String id;

    private String yardCode;
    private String yardCountry;
    private String yardCity;
    private String yardPincode;
    private String yardType;
    private String yardCapacity;
    private String yardTotalArea;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}
