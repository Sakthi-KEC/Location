package net.microservices.Location.repository;

import net.microservices.Location.model.Yard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class YardCustomRepositoryImpl implements YardCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Yard> findProperties(String yardCode,
                                     String yardCountry,
                                     String yardCity,
                                     String yardPincode,
                                     String yardType,
                                     String yardCapacity,
                                     String yardTotalArea,
                                     String createdBy,
                                     String createdDate)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (yardCode != null && !yardCode.isEmpty())
            criteria.add(Criteria.where("yardCode").is(yardCode));

        if (yardCountry != null && !yardCountry.isEmpty())
            criteria.add(Criteria.where("yardCountry").is(yardCountry));

        if (yardCity != null && !yardCity.isEmpty())
            criteria.add(Criteria.where("yardCity").is(yardCity));

        if (yardPincode != null && !yardPincode.isEmpty())
            criteria.add(Criteria.where("yardPincode").is(yardPincode));

        if (yardType != null && !yardType.isEmpty())
            criteria.add(Criteria.where("yardType").is(yardType));

        if (yardCapacity != null && !yardCapacity.isEmpty())
            criteria.add(Criteria.where("yardCapacity").is(yardCapacity));

        if (yardTotalArea != null && !yardTotalArea.isEmpty())
            criteria.add(Criteria.where("yardTotalArea").is(yardTotalArea));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (createdDate != null && !createdDate.isEmpty())
            criteria.add(Criteria.where("createdDate").is(createdDate));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Yard.class);
    }
}
