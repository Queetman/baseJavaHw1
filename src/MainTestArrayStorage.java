import ru.exception.StorageException;
import ru.model.Resume;
import ru.storage.AbstractArrayStorage;
import ru.storage.ListStorage;
import ru.storage.SortedArrayStorage;

import java.io.IOException;

public class MainTestArrayStorage {
    static final ListStorage ARRAY_STORAGE = new ListStorage();

    public static void main(String[] args) throws IOException {
        Resume r1 = new Resume("1");
        Resume r2 = new Resume("2");
        Resume r3 = new Resume("3");
        Resume r4 = new Resume("4");
        Resume r5 = new Resume("5");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        try{
        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        }
        catch (StorageException ex){

        }
        printAll();

        System.out.println("\nUpdating uuid3:");
        ARRAY_STORAGE.update(r3);
        printAll();

        System.out.println("Deleting uuid1:");
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() throws IOException {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
