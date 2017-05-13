package com.ifedoroff.greenbee.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Rostik on 13.05.2017.
 */
public class SearchRealTemperatureCriteria {
    @NotBlank(message = "date can't empty!")
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
