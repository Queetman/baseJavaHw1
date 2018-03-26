import java.util.Arrays;

/**
 * 1) если ты делаешь подобные переменные
 * private int storageLength = 10000;
 * то они должны быть final
 * <p>
 * 2) поле size не завел. Реализация методов изменится
 * 3) в методе size возвращай значение поля size
 * 4) в методе clear воспользуйся для обнуления массива методом Arrays.fill
 * 5) в методе get СРАЗУ возвращай результат либо null
 * 6) в методе get
 * storage[i].toString().equals(uuid)                  - этот кусок кода был в методе delete. Там и исправил
 * у нас в массиве хранятся Resume, а не String
 * нужно брать у Resume uuid и проерять его с тем, который пришел в метод
 */
public class ArrayStorage {

    private final int storageLength = 10000;
    private Resume[] storage = new Resume[storageLength];
    private int size;

    void clear() {
        Arrays.fill(storage, null);
    }

    //если ячейка пуста - сохранеиие.
    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {

        size = size();

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int j = 0;

//поиск номера элемента на удаление
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                j = i;
                break;
            }
        }
        //Сдвиг элементов, чтобы избежжать дырок в массиве
        for (int i = j; i < storageLength - 1; i++) {

            storage[i] = storage[i + 1];
        }

    }

    Resume[] getAll() {

        size = size();

        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        int arrSize = 0;

        for (Resume aStorage : storage) {
            if (aStorage != null) arrSize++;
            else break;
        }

        return arrSize;
    }
}
