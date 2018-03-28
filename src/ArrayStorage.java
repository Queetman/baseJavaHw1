import java.util.Arrays;

/**
 Привет
 1) storage[size()] = r;
 сразу обращайся к полю size в обход метода size()
 2) в методе delete нам не нужно проверять весь массив, т.к. большая его часть заполнена null
 лучше написать i < size
 3) также в методе delete получается такая штука, что
 если мы не находим в массиве резюме, которое нужно удалить (это первый цикл) и доходим до его последней ячейки,
 то после этого мы спокойненько переходим ко второму циклу (что, согласись не имеет смысла, если мы не нашли, что удалять) и снова проходим весь массив. Более того мы начинаем сдвигать его элементы, затирая резюме по адресу storage[0]
 4) Arrays.copyOfRange в нашем случаем можно заменить на Arrays.copyOf
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
        storage[size] = r;
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
        int j = -1;

//поиск номера элемента на удаление
        for (int i = 0; i <size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                j = i;
                size--;
                break;
            }
        }
        //если есть элемент для удаления
        if(j!=-1) {
            //Сдвиг элементов, чтобы избежжать дырок в массиве
            for (int i = j; i < STORAGE_LENGTH-1; i++) {
                storage[i] = storage[i + 1];
            }
        }
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
