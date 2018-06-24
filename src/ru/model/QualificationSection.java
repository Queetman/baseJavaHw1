package ru.model;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class QualificationSection extends Section {

    private List<Qualification> qualification;

    public QualificationSection(List<Qualification> qualification) {
        Objects.requireNonNull(qualification, "qualification must be not null");
        this.qualification = qualification;
    }

    public List<Qualification> getQualification() {
        return qualification;
    }

    public void setQualification(List<Qualification> qualification) {
        this.qualification = qualification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualificationSection that = (QualificationSection) o;
        return Objects.equals(qualification, that.qualification);
    }

    @Override
    public int hashCode() {

        return Objects.hash(qualification);
    }
}
