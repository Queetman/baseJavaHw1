package ru.model;

import java.util.Objects;

public class TextSection extends Section {

    private static final long serialVersionUID = 1L;

    private String text;

    public TextSection(String text) {
        Objects.requireNonNull(text, "text must be not null");
        this.text = text;
    }

    public TextSection() {
    }

    @Override
    public String getData() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text);
    }
}
