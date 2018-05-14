package ru.storage;

import ru.exception.NotExistStorageException;
import ru.model.Resume;

public abstract class AbstractStorage implements Storage {

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

    public abstract void save(Resume resume);

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
}
