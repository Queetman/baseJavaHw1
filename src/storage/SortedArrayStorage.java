package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage implements Comparator<Resume> {

    @Override
    protected int getIndex(String uuid) {

        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void sort() {

        Arrays.sort(storage, 0, size - 1, this::compare);
    }

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }
}