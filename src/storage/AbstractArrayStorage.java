package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);

        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;

        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            saveElement(resume, index);
            size++;

        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            size--;
            deleteElement(index);
            storage[size] = null;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}
