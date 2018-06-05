package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public abstract int size();

    public abstract void clear();

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);

        return getResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistedSearchKey(resume.getUuid());
        updateResume(searchKey, resume);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistedSearchKey(resume.getUuid());
        saveNewResume(resume, searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        deleteResume(searchKey);
    }

    public Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public void sortByFullName (List list){
         Collections.sort(list, Comparator.comparing(Resume::getFullName));
    }


    protected abstract boolean isExist(Object searchKey);

    public abstract List<Resume> getAllSorted();

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object index);

    protected abstract void updateResume(Object index, Resume resume);

    protected abstract void deleteResume(Object index);

    protected abstract void saveNewResume(Resume resume, Object index);
}
