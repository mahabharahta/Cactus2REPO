package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.SensorData;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class DataHandlerController {

    @GetMapping("/api/data")
    public String getSearchResultViaAjax(@RequestBody SensorData data, Errors errors)
    {
        return "succes";
    }
}
