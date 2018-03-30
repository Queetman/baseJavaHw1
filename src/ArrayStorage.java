import java.util.Arrays;

/**
 * по поводу метда delete
 * у тебя же есть уже готовый метод get для поиска резюме
 * используй его
 */
public class ArrayStorage {

    private final int STORAGE_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_LENGTH];
    private int size = 0;
    private int deleteNumber = -1;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                deleteNumber = i;
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {

        if (get(uuid) != null) {
            size--;
            replace(deleteNumber);
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private void replace(int j) {
        if (j != -1) {
            storage[j] = storage[size];
            storage[size] = null;

        }
    }

    int size() {
        return size;
    }
}
