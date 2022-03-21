package net.microservices.Location.repository;

import net.microservices.Location.model.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;


@Service
public class OfficeCustomRepositoryImpl implements OfficeCustomRepository
{
    @Autowired
    public ReactiveMongoTemplate template;

    @Override
    public Flux<Office> findProperties(String officeCode,
                                       String officeCountry,
                                       String officeCity,
                                       String officePincode,
                                       String officeCapacity,
                                       String officeTotalArea,
                                       String createdBy,
                                       String createdDate)
    {

        final Query query = new Query();
        final List<Criteria> criteria = new ArrayList<>();

        if (officeCode != null && !officeCode.isEmpty())
            criteria.add(Criteria.where("officeCode").is(officeCode));

        if (officeCountry != null && !officeCountry.isEmpty())
            criteria.add(Criteria.where("officeCountry").is(officeCountry));

        if (officeCity != null && !officeCity.isEmpty())
            criteria.add(Criteria.where("officeCity").is(officeCity));

        if (officePincode != null && !officePincode.isEmpty())
            criteria.add(Criteria.where("officePincode").is(officePincode));

        if (officeCapacity != null && !officeCapacity.isEmpty())
            criteria.add(Criteria.where("officeCapacity").is(officeCapacity));

        if (officeTotalArea != null && !officeTotalArea.isEmpty())
            criteria.add(Criteria.where("officeTotalArea").is(officeTotalArea));

        if (createdBy != null && !createdBy.isEmpty())
            criteria.add(Criteria.where("createdBy").is(createdBy));

        if (createdDate != null && !createdDate.isEmpty())
            criteria.add(Criteria.where("createdDate").is(createdDate));

        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        return template.find(query, Office.class);
    }
}
