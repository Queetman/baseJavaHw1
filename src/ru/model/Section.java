package ru.model;

import java.io.Serializable;

public abstract class Section implements Serializable {

    private static final long serialVersionUID=1L;

    protected abstract Object getData();
}
