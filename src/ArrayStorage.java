import java.util.Arrays;

/**
 1) у финальных переменных (storageLength) имена пишутся большими буквами
 2) Arrays.fill(storage, null);
 зачем нам обнулять полностью весь массив, если там 10000 элементов из которых всего несколько хранят в себе резюме. Посомтри метод fill с другой сигнатурой
 3) поле size - это полезный размер массива, заполненый резюме. это значит, что все, что находится за его пределами нас не интересует
 i < storage.length
 storage[i] == null
 это все лишнее
 4) после сигратуры метода не оставляй пустых строчек
 Resume get(String uuid)
 5) size = size(); - что это? size это поле, доступное отовсюду. Метод size() должен возвращать size, а не высчитывать его

 */
public class ArrayStorage {

    private final int storageLength = 10000;
    private Resume[] storage = new Resume[storageLength];
    private int size=0;

    void clear() {
        Arrays.fill(storage,0,size,null);
        size=0;
    }

    //если ячейка пуста - сохранеиие.
    void save(Resume r) {
      storage[size()]=r;
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
        size--;

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
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
