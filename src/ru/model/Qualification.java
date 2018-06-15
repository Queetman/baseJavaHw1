package ru.model;

import java.util.Objects;

public class Qualification {

    private String organisation;
    private String link;
    private String startDate;
    private String endDate;
    private String data;

    public Qualification(String organisation, String link, String startDate, String endDate, String data) {
        Objects.requireNonNull(organisation,"organisation must be not null");
        Objects.requireNonNull(startDate,"startDate must be not null");
        Objects.requireNonNull(endDate,"endDate must be not null");
        Objects.requireNonNull(data,"data must be not null");

        this.organisation = organisation;
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.data = data;
    }


}
