package ru.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organisation {

    private String organisation;
    private String link;
    private List<OrganisationPosition> positions = new ArrayList<>();


    public Organisation(String organisation, String link, OrganisationPosition organisationPosition) {
        Objects.requireNonNull(organisation, "organisation must be not null");
        Objects.requireNonNull(organisationPosition, "OrganisationPosition must be not null");
        this.organisation = organisation;
        this.link = link;
        positions.add(organisationPosition);
    }

    @Override
    public String toString() {
        String stringPositions = "";

        for (OrganisationPosition orgPos :
                positions) {
            stringPositions += "Начало работы: " + orgPos.getStartDate() + "\n" +
                    "Конец работы: " + orgPos.getEndDate() + "\n" +
                    "Описание: " + orgPos.getData() + "\n";
        }
        return "Организация: " + organisation + "\n" +
                "Сайт: " + link + "\n" + stringPositions;
    }

    public void addOrganisationPosition(OrganisationPosition organisationPosition) {
        positions.add(organisationPosition);
    }
}
