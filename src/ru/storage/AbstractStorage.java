package ru.storage;

import ru.exception.ExistStorageException;
import ru.exception.NotExistStorageException;
import ru.exception.StorageException;
import ru.model.Resume;

import static ru.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractStorage implements Storage {

    protected int size = 0;

    public abstract int size();

    public abstract void clear();

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
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
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", resume.getUuid());
        } else {
            saveNewResume(resume, index);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    public abstract Resume[] getAll();

    protected abstract int getIndex(String uuid);

    protected abstract Resume getResume(int index);

    protected abstract void updateResume(int index, Resume resume);

    protected abstract void deleteResume(int index);

    protected abstract void saveNewResume(Resume resume, int index);
}
