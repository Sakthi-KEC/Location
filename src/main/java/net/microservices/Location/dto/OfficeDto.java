package net.microservices.Location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDto
{
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
