package net.microservices.Location.appUtil;

import net.microservices.Location.dto.WarehouseDto;
import net.microservices.Location.model.Warehouse;
import org.springframework.beans.BeanUtils;


public class WarehouseAppUtil
{
    public static Warehouse DtoToEntity(WarehouseDto dto)
    {
        Warehouse entity=new Warehouse();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    public static WarehouseDto EntityToDto(Warehouse entity)
    {
        WarehouseDto dto=new WarehouseDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
