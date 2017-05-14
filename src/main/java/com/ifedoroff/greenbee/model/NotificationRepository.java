package com.ifedoroff.greenbee.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Rostik on 14.05.2017.
 */
public interface  NotificationRepository extends MongoRepository<Notification, Long> {

    List<Notification> findByName(String name);

    List<Notification> findAll();

    Notification insert(Notification humidity);
}