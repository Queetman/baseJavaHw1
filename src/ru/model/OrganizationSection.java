package ru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends Section {

    private List<Organization> qualification;

    public OrganizationSection(List<Organization> qualification) {
        Objects.requireNonNull(qualification, "qualification must be not null");
        this.qualification = qualification;
    }

    public OrganizationSection(Organization ... organizations) {
       this(Arrays.asList(organizations));
    }

    @Override
    public Object getData() {

        List<String> data = new ArrayList();

        for (Organization q :
                qualification) {
            data.add(q.toString());
        }
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualification);
    }
}
