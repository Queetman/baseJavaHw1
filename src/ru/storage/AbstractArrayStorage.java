package ru.storage;

import ru.exception.StorageException;
import ru.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

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
    public void saveNewResume(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", resume.getUuid());
        } else {
            saveElement(resume, index);
            size++;
        }
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage[index];
    }

    @Override
    protected void updateResume(Integer index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(Integer index) {
        size--;
        deleteElement(index);
        storage[size] = null;
    }

    @Override
    protected List<Resume> getSortableList() {
        return new ArrayList(Arrays.asList(Arrays.copyOf(storage,size())));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}