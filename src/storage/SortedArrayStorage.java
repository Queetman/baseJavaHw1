package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();

        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume resume, int index) {
        int insertIndex = -index - 1;

        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = resume;

    }

    @Override
    protected void deleteElement(int index) {
        int deleteIndex = size - index;

        //Проверка на пустоту storage
        if (deleteIndex > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteIndex);
        }
    }
}