package ru.storage;

import ru.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    private ArrayList<Resume> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    protected int getIndex(String uuid) {

        for (int i = 0; i < size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getResume(int index) {
        return list.get(index);
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        list.set(index,resume);
    }

    @Override
    protected void deleteResume(int index) {
        list.remove(index);
    }

    @Override
    protected void saveNewResume(Resume resume, int index) {
        list.add(resume);
    }


}



