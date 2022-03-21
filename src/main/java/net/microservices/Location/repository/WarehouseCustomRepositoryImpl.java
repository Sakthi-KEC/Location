package net.microservices.Location.repository;

import net.microservices.Location.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class WarehouseCustomRepositoryImpl implements WarehouseCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Warehouse> findProperties(String warehouseCode,
                                          String warehouseCountry,
                                          String warehouseCity,
                                          String warehousePincode,
                                          String warehouseCapacity,
                                          String warehouseTotalArea,
                                          String createdBy,
                                          String createdDate)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (warehouseCode != null && !warehouseCode.isEmpty())
            criteria.add(Criteria.where("warehouseCode").is(warehouseCode));

        if (warehouseCountry != null && !warehouseCountry.isEmpty())
            criteria.add(Criteria.where("warehouseCountry").is(warehouseCountry));

        if (warehouseCity != null && !warehouseCity.isEmpty())
            criteria.add(Criteria.where("warehouseCity").is(warehouseCity));

        if (warehousePincode != null && !warehousePincode.isEmpty())
            criteria.add(Criteria.where("warehousePincode").is(warehousePincode));

        if (warehouseCapacity != null && !warehouseCapacity.isEmpty())
            criteria.add(Criteria.where("warehouseCapacity").is(warehouseCapacity));

        if (warehouseTotalArea != null && !warehouseTotalArea.isEmpty())
            criteria.add(Criteria.where("warehouseTotalArea").is(warehouseTotalArea));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (createdDate != null && !createdDate.isEmpty())
            criteria.add(Criteria.where("createdDate").is(createdDate));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Warehouse.class);
    }
}
