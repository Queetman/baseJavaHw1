package ru.storage;

import ru.model.Resume;

import java.io.IOException;
import java.util.List;

public interface Storage {

    void clear() throws IOException;

    void update(Resume resume);

    void save(Resume resume) throws IOException;

    Resume get(String uuid) throws IOException;

    void delete(String uuid);

    List<Resume> getAllSorted() throws IOException;

    int size();
}
