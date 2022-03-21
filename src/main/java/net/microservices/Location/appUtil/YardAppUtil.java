package net.microservices.Location.appUtil;

import net.microservices.Location.dto.YardDto;
import net.microservices.Location.model.Yard;
import org.springframework.beans.BeanUtils;


public class YardAppUtil
{
    public static Yard DtoToEntity(YardDto dto)
    {
        Yard entity=new Yard();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static YardDto EntityToDto(Yard entity)
    {
        YardDto dto=new YardDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
