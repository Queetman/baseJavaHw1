import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[100000];
    Resume nullElement = new Resume();

    public ArrayStorage() {
        nullElement.uuid = "zzz";
        Arrays.fill(storage, nullElement);
    }


    void clear() {
        nullElement.uuid = "zzz";
        Arrays.fill(storage, nullElement);
    }

    //если ячейка пуста - сохранеиие.
    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(nullElement)) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {

        Resume resume = null;
        Resume notExistResume = new Resume();
        notExistResume.uuid = "Element does not exist";


        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
                break;


            } else resume = notExistResume;

        }

        return resume;

    }

    void delete(String uuid) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = nullElement;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
  /*
   Вар.1
   Resume[] getAll() {

        Resume[] notNullResume = new Resume[10000];
        Arrays.fill(notNullResume,nullElement);
        int j = 0;

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != nullElement) {
                notNullResume[j] = storage[i];
                j++;
            }
        }

        return notNullResume;
    }*/
//вап 2 поучше
    Resume[] getAll() {

        Resume[] resumeWithutNull;

        Arrays.sort(storage, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                return o1.uuid.compareTo(o2.uuid);
            }
        });
        resumeWithutNull = Arrays.copyOfRange(storage, 0, size());

        return resumeWithutNull;
    }

    int size() {
        int i = 0;

        for (Resume aStorage : storage) {
            if (aStorage != nullElement) i++;

        }
        return i;
    }
}
