package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class RealTimeMonitorController {


    @Autowired
    private HumidityDataRepository humidityDataRepository;

    @Autowired
    private TemperatureDataRepository temperatureDataRepository;

    @PostMapping("/api/realtime/searchfirst")
    public ResponseEntity<?> getSearchResult(@Valid @RequestBody SearchRealTemperatureCriteria search, Errors errors)
    {
        PageResponseBody respond = new PageResponseBody();
        //logic
        return  ResponseEntity.ok(respond);
    }

    @GetMapping("/api/test")
    public  ResponseEntity<?> getTest(@RequestBody SearchRealTemperatureCriteria search, Errors errors)
    {
        PageResponseBody respond = new PageResponseBody();
        return  ResponseEntity.ok(respond);
    }
}
