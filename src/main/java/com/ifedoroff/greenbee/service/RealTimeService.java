package com.ifedoroff.greenbee.service;

import com.ifedoroff.greenbee.SpringBootApplication;
import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;
import com.ifedoroff.greenbee.model.Temperature;
/**
 * Created by Rostik on 13.05.2017.
 */
@Service
public class RealTimeService {

    public SensorData getSearchResult(String id)
    {
        SensorData result = new SensorData();
        result.setId(id);
        Temperature temperature = getLastByDate(Temperature.class);
        Light light = getLastByDate(Light.class);
        Humidity humidity = getLastByDate(Humidity.class);
        result.setTemperature(temperature.getValue());
        result.setHumidity(humidity.getValue());
        result.setLight(light.getValue());
        //logic
        return  result;
    }


    // refactor next

    private <T extends IData> T getLastByDate(Class<T> type)
    {
        MongoOperations mongoOperations = SpringBootApplication.ctx.getBean(MongoOperations.class);
        Query q = new Query();
        q.limit(1);
        q.with(new Sort(Sort.Direction.DESC, "date"));
        List<T> t = mongoOperations.find(q,type);
        return  t.get(0);
    }
}
