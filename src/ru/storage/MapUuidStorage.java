package ru.storage;

import ru.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

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
    protected boolean isExist(String searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected List<Resume> getSortableList() {
        return new ArrayList<>(map.values());
    }

    @Override
    protected Resume getResume(String index) {
        return map.get(index);
    }

    @Override
    protected void updateResume(String index, Resume resume) {
        map.put(index, resume);
    }

    @Override
    protected void deleteResume(String index) {
        map.remove(index);
    }

    @Override
    protected void saveNewResume(Resume resume, String index) {
        map.put(index, resume);
    }

    //должен возвращать uuid
    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

}




