package ru.model;

import java.io.Serializable;
import java.util.Objects;

public class Links implements Serializable {

    private static final long serialVersionUID=1L;

   private String name;
   private String link;

    public Links(String name, String link) {
        Objects.requireNonNull(name,"name must be not null");
        Objects.requireNonNull(link,"link must be not null");
        this.name = name;
        this.link = link;
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
        Links links = (Links) o;
        return Objects.equals(name, links.name) &&
                Objects.equals(link, links.link);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, link);
    }
}
