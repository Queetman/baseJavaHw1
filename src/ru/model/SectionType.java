package ru.model;

public enum SectionType {
    PERSONAL("Личные качества"),//textSection
    OBJECTIVE("Позиция"),//textSection
    ACHIEVEMENT("Достижения"),//listSection
    QUALIFICATIONS("Квалификация"),//listSection
    EXPERIENCE("Опыт работы"),//OrganisationSection
    EDUCATION("Образование");//OrganisationSection

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
