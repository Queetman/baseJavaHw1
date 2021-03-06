package ru.storage;

import ru.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid,"name");
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
        int deleteIndex = size - index - 1;

        //Проверка на пустоту ru.storage
        if (deleteIndex > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteIndex);
        }
    }
}