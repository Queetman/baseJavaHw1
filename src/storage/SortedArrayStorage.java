package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {

        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume r, int index) {

        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx + 1);
        storage[insertIdx] = r;

    }

    @Override
    protected void deleteElement(int index) {

        int deleteIdx = size - index - 1;
        //Проверка на пустоту storage
        if (deleteIdx > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteIdx);
        }
    }
}