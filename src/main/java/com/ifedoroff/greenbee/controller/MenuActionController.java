package com.ifedoroff.greenbee.controller;

import com.ifedoroff.greenbee.model.DevicesRepository;
import com.ifedoroff.greenbee.model.DevicesSearchCriteria;
import com.ifedoroff.greenbee.model.PageResponseBody;
import com.ifedoroff.greenbee.model.SearchRealTemperatureCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ifedoroff.greenbee.model.Device;

import java.util.List;

/**
 * Created by Rostik on 13.05.2017.
 */
@RestController
public class MenuActionController {


    @Autowired
    DevicesRepository devicesRepository;

    @GetMapping("/api/navigation/devs")
    public ResponseEntity<?> getListDevices(@RequestBody DevicesSearchCriteria search, Errors errors)
    {
        PageResponseBody respond = new PageResponseBody();
        if (errors.hasErrors())
        {
            respond.setMsg("bad request");
            ResponseEntity.badRequest().body(respond);
        }

        List<Device> devices = devicesRepository.findAll();

        // build page


        return  ResponseEntity.ok(respond);
    }
}
