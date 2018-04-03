import java.util.Arrays;

/**
 */
public class ArrayStorage {

    private final int STORAGE_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_LENGTH];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index != -1)
            return storage[index];
        else return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            size--;
            replace(index);
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void replace(int index) {
        if (index != -1) {
            storage[index] = storage[size];
            storage[size] = null;
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
