package ru.storage;

import ru.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {

    private List<Resume> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != -1;
    }

    @Override
    protected List<Resume> getSortableList() {
        return new ArrayList<>(list);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getResume(Integer index) {
        return list.get(index);
    }

    @Override
    protected void updateResume(Integer index, Resume resume) {
        list.set(index, resume);
    }

    @Override
    protected void deleteResume(Integer index) {
        list.remove(index.intValue());
    }

    @Override
    protected void saveNewResume(Resume resume, Integer index) {
        list.add(resume);
    }
}



