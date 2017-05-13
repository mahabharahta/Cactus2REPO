package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Rostik on 13.05.2017.
 */

public interface  HumidityDataRepository extends MongoRepository<Temperature, Long> {

    Temperature findByName(String name);
}