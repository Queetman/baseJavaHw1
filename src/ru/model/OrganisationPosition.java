package ru.model;

import java.time.LocalDate;

public class OrganisationPosition {
   private LocalDate startDate;
   private LocalDate endDate;
    private String data;

    public OrganisationPosition(LocalDate startDate,LocalDate endDate, String data) {
        this.startDate=startDate;
        this.endDate= endDate;
        this.data=data;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "OrganisationPosition{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", data='" + data + '\'' +
                '}';
    }
}
