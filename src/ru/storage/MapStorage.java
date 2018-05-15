package ru.storage;


import ru.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map map = new HashMap<String, Resume>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public void update(Resume resume) {
    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }

    @Override
    protected void updateResume(int index, Resume resume) {
    }

    @Override
    protected void deleteResume(int index) {
    }

    @Override
    protected void saveNewResume(Resume resume, int index) {
    }
}




