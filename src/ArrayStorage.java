import java.util.Arrays;

/**
 * 1) //если ячейка пуста - сохранеиие.
 * данный комментарий не отражает суть метода, т.к. в методе мы ни где не проверяем, что ячейка пуста.
 * 2) в методе get у if забыл {}
 * 3) метод delete делает два разных действия - ищет в начале элемент, который нужно удалить (первый цикл), а потом во втором цикле делает сдвиг
 * эти действия тянут на два отдельных метода
 * 4) забываешь делать ctrl + alt + L
 * 5) //поиск номера элемента на удаление
 * данный комментарий "болтается" в пространстве
 * вообще у нас очень простые методы с простой реализацией. Нужно стараться не комментарии писать, а код, который будет тебе самому понятен
 * 6) storage[i] = storage[i + 1]
 * ты делаешь сдвиг элементов, но послений элемент у тебя все равно остается
 * например
 * 1234
 * тебе надо удалить 2
 * 1344
 * вот что у тебя на выходе получается (edited)
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
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int j = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                j = i;
                size--;
                break;
            }
        }
        shiftElement(j);
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private void shiftElement(int j) {
        if (j != -1) {
            //Сдвиг элементов, чтобы избежать дырок в массиве
            for (int i = j; i < STORAGE_LENGTH - 1; i++) {
                storage[i] = storage[i + 1];
                if (storage[i + 1] == null) break;
            }
        }
    }

    int size() {
        return size;
    }
}
