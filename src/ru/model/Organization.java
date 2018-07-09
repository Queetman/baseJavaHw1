package ru.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {

    private String organisation;
    private String link;
    private List<Position> positions = new ArrayList<>();


    public Organization(String organisation, String link, Position position) {
        Objects.requireNonNull(organisation, "organisation must be not null");
        Objects.requireNonNull(position, "Position must be not null");
        this.organisation = organisation;
        this.link = link;
        positions.add(position);
    }

    public Organization(String link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }

    @Override
    public String toString() {
        String stringPositions = "";

        for (Position orgPos :
                positions) {
            stringPositions += "Начало работы: " + orgPos.getStartDate() + "\n" +
                    "Конец работы: " + orgPos.getEndDate() + "\n" +
                    "Описание: " + orgPos.getData() + "\n";
        }
        return "Организация: " + organisation + "\n" +
                "Сайт: " + link + "\n" + stringPositions;
    }

    public void addOrganisationPosition(Position position) {
        positions.add(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(organisation, that.organisation) &&
                Objects.equals(link, that.link) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {

        return Objects.hash(organisation, link, positions);
    }

    public static class Position {
       private LocalDate startDate;
       private LocalDate endDate;
        private String data;

        public Position(LocalDate startDate, LocalDate endDate, String data) {
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
            return "Position{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", data='" + data + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position that = (Position) o;
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(data, that.data);
        }

        @Override
        public int hashCode() {

            return Objects.hash(startDate, endDate, data);
        }
    }
}
