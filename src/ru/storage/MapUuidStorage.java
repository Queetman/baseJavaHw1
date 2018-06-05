package ru.storage;

import ru.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(map.values());
        Collections.sort(list);

        return list;
    }

    @Override
    protected Resume getResume(Object index) {
        return map.get(index);
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        map.put((String) index, resume);
    }

    @Override
    protected void deleteResume(Object index) {
        map.remove(index);
    }

    @Override
    protected void saveNewResume(Resume resume, Object index) {
        map.put((String) index, resume);
    }

    //должен возвращать uuid
    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

}




