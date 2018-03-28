import java.util.Arrays;

/**
 * ) у финальных переменных (storageLength) имена пишутся большими буквами - вот так STORAGE_LENGTH
 * 2) перед каждым коммитом выделяй код и нажимай Ctrl + Alt + L
 * 3) метод delete() написан так, что, если в массиве нет ресюме и мы ничего не удаляем, то size-- все равно отрабатывает
 * поставь size-- после j = i;
 */
public class ArrayStorage {

    private final int STORAGE_LENGTH = 10000;
    private Resume[] storage = new Resume[STORAGE_LENGTH];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //если ячейка пуста - сохранеиие.
    void save(Resume r) {
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int j = 0;

//поиск номера элемента на удаление
        for (int i = 0; i < STORAGE_LENGTH; i++) {
            if (storage[i].uuid.equals(uuid)) {
                j = i;
                size--;
                break;
            }
        }
        //Сдвиг элементов, чтобы избежжать дырок в массиве
        for (int i = j; i < STORAGE_LENGTH - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
