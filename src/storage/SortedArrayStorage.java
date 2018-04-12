package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {

        int index = getIndex(r.getUuid());

        if (index == -1) {
            System.out.println("Резюме с uuid :" + r.getUuid() + " не существует");
        } else {
            storage[getIndex(r.getUuid())] = r;
            sort();
        }
    }

    @Override
    public void save(Resume r) {

        if (getIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
            sort();
        }
    }

    @Override
    public void delete(String uuid) {

        int index = getIndex(uuid);

        if (index == -1) {
            System.out.println("Резюме с uuid :" + uuid + " не существует");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            sort();
        }
    }

    @Override
    protected int getIndex(String uuid) {

        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    private void sort() {

        Arrays.sort(storage, 0, size - 1, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return o1.getUuid().compareTo(o2.getUuid());
            }
        });
    }
}