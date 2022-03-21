package net.microservices.Location.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "office")
public class Office
{
    @Id
    private String id;

    private String officeCode;
    private String officeCountry;
    private String officeCity;
    private String officePincode;
    private String officeContact;
    private String officeCapacity;
    private String officeTotalArea;
    private Boolean status;
    private Boolean saved;
    private String createdBy;
    private String createdDate;
}
