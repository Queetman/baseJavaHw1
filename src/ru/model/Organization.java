package ru.model;

import ru.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    private String organisation;
    private String link;
    private List<Position> positions = new ArrayList<>();


    public Organization(String organisation, String link, Position ...positions) {
        Objects.requireNonNull(organisation, "organisation must be not null");
        Objects.requireNonNull(positions, "Position must be not null");
        this.organisation = organisation;
        this.link = link;
    }

    public Organization(String link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }

    public Organization() {
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String data;
        private String description;

        public Position(LocalDate startDate, LocalDate endDate, String data, String description) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.data = data;
            this.description=description;
        }

        public Position() {
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
        public String getDescription() {
            return description;
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

    public String getOrganisation() {
        return organisation;
    }

    public String getLink() {
        return link;
    }

    public List<Position> getPositions() {
        return positions;
    }
}
