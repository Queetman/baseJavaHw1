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

    void update(Resume r) {

        if (hasResume(r)) {
        }
    }

    void save(Resume r) {

        if (size < STORAGE_LENGTH) {

            if (!hasResume(r)) {
                storage[size] = r;
                size++;
            }
        } else System.out.println("Хранилище переполнено");
    }

    private boolean hasResume(Resume r) {
        boolean hasResume = false;

        for (int i = 0; i < size; i++) {
            if (storage[i].equals(r)) {
                System.out.println("Резюме уже есть в базе.");
                hasResume = true;
            }
        }
        return hasResume;
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);
        Resume res = null;

        if (index != -1) {
            res = storage[index];
        } else System.out.println("Резюме нет в базе");

        return res;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            size--;
            replace(index);
        } else System.out.println("Резюме нет в базе");
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
