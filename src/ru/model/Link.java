package ru.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String link;

    public Link(String name, String link) {
        Objects.requireNonNull(name, "name must be not null");
        Objects.requireNonNull(link, "link must be not null");
        this.name = name;
        this.link = link;
    }

    public Link() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(name, link.name) &&
                Objects.equals(this.link, link.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, link);
    }
}
