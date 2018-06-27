package ru.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organisation {

    private String organisation;
    private String link;
    private List <LocalDate> startDateList=new ArrayList<>();
    private List<LocalDate> endDateList=new ArrayList<>();;
    private List<String> dataList=new ArrayList<>();;

    public Organisation(String organisation, String link, LocalDate startDate, LocalDate endDate, String data) {
        Objects.requireNonNull(organisation, "organisation must be not null");
        Objects.requireNonNull(startDate, "startDate must be not null");
        Objects.requireNonNull(endDate, "endDate must be not null");
        Objects.requireNonNull(data, "data must be not null");

        this.organisation = organisation;
        this.link = link;
        startDateList.add(startDate);
        endDateList.add(endDate);
        dataList.add(data);
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getLink() {
        return link;
    }

    public List<LocalDate> getStartDateList() {
        return startDateList;
    }

    public List<LocalDate> getEndDateList() {
        return endDateList;
    }

    public List<String> getDataList() {
        return dataList;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "organisation='" + organisation + '\'' +
                ", link='" + link + '\'' +
                ", startDateList=" + startDateList +
                ", endDateList=" + endDateList +
                ", dataList=" + dataList +
                '}';
    }

    public void addNewWork(LocalDate newStartDate, LocalDate newEndDate, String data) {
        startDateList.add(newEndDate);
        endDateList.add(newEndDate);
        dataList.add(data);
    }
}
