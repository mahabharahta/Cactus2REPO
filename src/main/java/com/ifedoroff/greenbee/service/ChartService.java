package com.ifedoroff.greenbee.service;

import com.ifedoroff.greenbee.SpringBootApplication;
import com.ifedoroff.greenbee.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rostik on 14.05.2017.
 */
@Service
public class ChartService {

    public <T extends IData> List<T> getValues(Class<T> type,int num)
    {
        MongoOperations mongoOperations = SpringBootApplication.ctx.getBean(MongoOperations.class);
        Query q = new Query();
        q.limit(num);
        q.with(new Sort(Sort.Direction.DESC, "date"));
        List<T> t = mongoOperations.find(q,type);
        return  t;
    }
}
