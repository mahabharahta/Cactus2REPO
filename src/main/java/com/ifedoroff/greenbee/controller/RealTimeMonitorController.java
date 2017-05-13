package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class RealTimeMonitorController {


    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private HumidityDataRepository humidityDataRepository;

    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @Autowired
    private LightDataRepository lightDataRepository;

    @PostMapping("/api/realtime/searchreal")
    public ResponseEntity<?> getSearchResult(@Valid @RequestBody SearchRealTemperatureCriteria search, Errors errors)
    {
        PageResponseBody respond = new PageResponseBody();


        //logic
        return  ResponseEntity.ok(respond);
    }


    // refactor next

    private <T extends IData> T getLastByDate(Class<T> type)
    {
        Query q = new Query();
        q.limit(1);
        q.with(new Sort(Sort.Direction.DESC, "date"));
        List<T> t = mongoOperations.find(q,type);
        return  t.get(0);
    }

}
