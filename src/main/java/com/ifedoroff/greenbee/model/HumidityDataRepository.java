package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Rostik on 13.05.2017.
 */

public interface  HumidityDataRepository extends MongoRepository<Humidity, Long> {

    List<Humidity> findByDate(String uuid, String date);

    Humidity insert(Humidity humidity);
}
