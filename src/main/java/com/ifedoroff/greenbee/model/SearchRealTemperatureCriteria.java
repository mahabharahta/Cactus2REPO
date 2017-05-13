package com.ifedoroff.greenbee.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Rostik on 13.05.2017.
 */
public class SearchRealTemperatureCriteria {
    @NotBlank(message = "date can't empty!")
    String date;

    @NotBlank(message = "id can't be empty")
    String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
