import java.util.Arrays;

/**
 *Скорректированный вариант в соответствии с замечаниями:
 * 1) не оставляй в коде так называемых "якорей" - закомментированного кода
 2) зачем ты в конструкторе инициализируешь все ячейки storage, Resume с uuid = zzz?
 3) в clear() заполняй ячейки массива null
 4) для того что бы не подсчитывать каждый раз в методе size() длину массива, заведи дополнительное поле size
 используя эту переменную, реализация многих методов частично изменится
 5) в методе get() возвращай сразу storage[i] или null. Первые три строчки лишние
 6) по заданию у нас в массиве не может быть "дырок". Это значит, что все элементы плотно прилегают друг к другу. При удалении учитывай это. Ни null ни zzz не должно быть между элементами
 7) порядок элементов в getAll() нам пока не нужен. Просто возвращай Arrays.copyOfRange()
 */
public class ArrayStorage {

    private int storageLength = 10000;
    private Resume[] storage = new Resume[storageLength];

    void clear() {

        if (size() != 0) {
            for (int i = 0; i < storage.length; i++) {
                storage[i] = null;
            }
        } else System.out.println("Storage is empty");
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

        Resume resume = null;

        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
                break;

            } else resume = null;
        }
        return resume;
    }

    void delete(String uuid) {
        int j = 0;

//поиск номера элемента на удаление
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
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

        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        int i = 0;

        for (Resume aStorage : storage) {
            if (aStorage != null) i++;
        }
        return i;
    }
}
