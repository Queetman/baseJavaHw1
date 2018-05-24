package ru.storage;


import ru.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

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
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    protected Integer getSearchKey(String uuid) {
        return 0;
    }

    @Override
    protected Resume getResume(Object index) {
        return null;
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
    }

    @Override
    protected void deleteResume(Object index) {
    }

    @Override
    protected void saveNewResume(Resume resume, Object index) {
    }
}




