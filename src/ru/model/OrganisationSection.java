package ru.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganisationSection extends Section {

    private List<Organisation> qualification;

    public OrganisationSection(List<Organisation> qualification) {
        Objects.requireNonNull(qualification, "qualification must be not null");
        this.qualification = qualification;
    }

    @Override
    public Object getData() {

        List<String> data = new ArrayList();

        for (Organisation q :
                qualification) {
            data.add(q.toString());
        }
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganisationSection that = (OrganisationSection) o;
        return Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualification);
    }
}
