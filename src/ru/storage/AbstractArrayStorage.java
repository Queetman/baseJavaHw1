package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void saveNewResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", resume.getUuid());
        } else {
            saveElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected void updateResume(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void deleteResume(Object index) {
        size--;
        deleteElement((Integer) index);
        storage[size] = null;
    }

    @Override
    protected List<Resume> getSortableList() {

        return new ArrayList(Arrays.asList(Arrays.copyOf(storage, size())));
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}