package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Rostik on 13.05.2017.
 */
public interface DevicesRepository extends MongoRepository<Device, Long> {
        List<Device> findAll();

        Device insert(Device device);
}
