package ru.model;


import java.util.*;

public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    private Map<Contacts,String> contacts =new EnumMap<>(Contacts.class);
    private Map<SectionType,Section> sections =new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {

        Objects.requireNonNull(uuid, "uuid must be not null");
        Objects.requireNonNull(fullName, "fullName must be not null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact (Contacts contact){
       return contacts.get(contact);
    }

    public Object getSectionTypeData (SectionType type ){

        return sections.get(type).getData();
    }

    public void setContact(String contact,Contacts contactName){
        contacts.put(contactName,contact);
    }

    public void setSectionType(Section section,SectionType sectionName){
        sections.put(sectionName,section);
    }

}