package storage;

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
            System.out.println("Resume with uuid = " + uuid + "does not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index < 0) {
            System.out.println("Resume with uuid :" + resume.getUuid() + " does not exist");
        } else {
            storage[index] = resume;

        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());

        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " already exists");
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
            System.out.println("Resume with uuid :" + uuid + " does not exist");
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

    //тестовый метод
    public void getIndexes() {

        for (int i = 0; i < size; i++) {
            System.out.println("Resume with uuid: " + storage[i].getUuid() + " have index " + getIndex(storage[i].getUuid()));
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}
