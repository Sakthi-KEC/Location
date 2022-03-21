package net.microservices.Location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "train")
public class YardDto
{
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
