import java.util.Arrays;

/**
 * 1) else System.out.println("...");
 * в Java принято ВСЕГДА ставить {}
 * <p>
 * 2) "Резюме нет в базе" - это не информативное сообщение.
 * Надо что-то на подобии: Обновление невозможно: отсутствует резюме с uuid =
 * <p>
 * 3) у тебя уже есть метод для поиска резюме - getIndex,
 * а hasResume дублирует финкционал этого метода
 * <p>
 * 4) в методе get сразу возвращай результат либо null без создания промежуточной переменной
 * <p>
 * 5) в методе delete у тебя есть проверка
 * if (index != -1)
 * а в теле if есть метод replace в котором снова есть эта проверка. Зачем два раза делать одну и туже проверку?
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
        int index = getIndex(r.uuid);

        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Обновление невозможно: отсутствует резюме с uuid = " + r.uuid);
        }
    }

    void save(Resume r) {

        if (size < STORAGE_LENGTH) {

            if (getIndex(r.uuid) == -1) {
                storage[size] = r;
                size++;
            }
        } else {
            System.out.println("Хранилище переполнено");
        }
    }

    Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Резюме c uuid " + uuid + " нет в базе.");
        }
        return null;
    }

    void delete(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            size--;
            replace(index);
        } else {
            System.out.println("Резюме c uuid " + uuid + " нет в базе.");
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
        storage[index] = storage[size];
        storage[size] = null;
    }

    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
