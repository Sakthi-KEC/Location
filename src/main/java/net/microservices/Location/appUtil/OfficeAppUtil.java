package net.microservices.Location.appUtil;

import net.microservices.Location.dto.OfficeDto;
import net.microservices.Location.model.Office;
import org.springframework.beans.BeanUtils;


public class OfficeAppUtil
{
    public static Office DtoToEntity(OfficeDto dto)
    {
        Office entity=new Office();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static OfficeDto EntityToDto(Office entity)
    {
        OfficeDto dto=new OfficeDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
