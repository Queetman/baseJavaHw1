package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateResume(index, resume);
        }
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            list.add(resume);
        }
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
        list.remove(index);
        list.add(resume);
    }

    @Override
    protected void deleteResume(int index) {
        list.remove(index);
    }


}



