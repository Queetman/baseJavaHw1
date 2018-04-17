package storage;

import model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {

        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveElement(Resume r, int index) {
        storage[size] = r;
    }

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[size - 1];
    }
}