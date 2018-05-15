package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.exception.StorageException;
import ru.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    public int size() {
        return size;
    }


    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        size--;
        deleteElement(index);
        storage[size] = null;
    }

    @Override
    protected void saveNewResume(Resume resume, int index) {
        saveElement(resume, index);
        size++;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}