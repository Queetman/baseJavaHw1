package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage implements Comparator<Resume> {

    @Override
    protected int getIndex(String uuid) {

        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveElement(Resume r, int index) {

        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx + 1);
        storage[insertIdx] = r;
        //https://juja.com.ua/java/algorithms/sorting-optimizing/
        //  1)входная величина index  получена с помощью BinarySearch. Свойство этого поиска:
        //при поиске отсутствующего в массиве элемента он выдаст индекс ячейки
        // с максимально приближенным, в соответствующем направлении,
        // значением к искомому, но в отрицательном значении и увеличенном на единицу:  -(low + 1).
        //т.е. мы получили отрицательный индекс т.к. элемента нет(его добавить надо) и переводим его в нормальный :-index - 1
        //Далее надо создать дырку в массиве, куда надо вставить значение.
        //далее в arraycopy  мы берем данные с позиции insertIdx длины size-insertIdx+1 и копируем их
        //с позиции insertIdx+1. Таким образом, у нас есть 2 одиаковых значеня на поз. insertIdx и insertIdx+1
        //отсалось просто заменить одно значение на нужное  storage[insertIdx]=r;
    }

    @Override
    protected void deleteElement(int index) {

        int deleteIdx = size - index - 1;
        //Проверка на пустоту storage
        if (deleteIdx > 0) {
            System.arraycopy(storage, index + 1, storage, index, deleteIdx);
        }
    }

    @Override
    public int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }
}