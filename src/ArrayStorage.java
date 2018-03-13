import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        storage = new Resume[10000];
    }

    //если ячейка пуста - сохранеиие.
    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) storage[i] = r;
        }
    }

    Resume get(String uuid) {

        Resume resume = null;


        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                resume = storage[i];
            }

        }
        return resume;

    }

    void delete(String uuid) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        Resume[] notNullResume = new Resume[10000];
        int j = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                notNullResume[j] = storage[i];
                j++;
            }
        }

        return notNullResume;
    }

    int size() {
        int i = 0;

        for (int j = 0; j < storage.length; j++) {
            if (storage[j] != null) j++;

        }
        return i;
    }
}
