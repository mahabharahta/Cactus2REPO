package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Rostik on 13.05.2017.
 */
public interface LightDataRepository  extends MongoRepository<Light, Long> {
    List<Light> findByDate(String uuid, String date);

    Light insert(Light humidity);
}
