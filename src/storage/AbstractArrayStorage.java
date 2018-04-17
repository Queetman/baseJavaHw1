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
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume r) {

        int index = getIndex(r.getUuid());

        if (index < 0) {
            System.out.println("Резюме с uuid :" + r.getUuid() + " не существует");
        } else {
            storage[index] = r;

        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());

        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {

            saveElement(r, index);
            size++;

        }
    }

    public void delete(String uuid) {

        int index = getIndex(uuid);

        if (index < 0) {
            System.out.println("Резюме с uuid :" + uuid + " не существует");
        } else {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deleteElement(int index);


}
