import model.Resume;
import storage.AbstractArrayStorage;
import storage.ArrayStorage;
import storage.SortedArrayStorage;

/**
 * Test for com.urise.webapp.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final AbstractArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        Resume r2 = new Resume();
        Resume r3 = new Resume();
        Resume r4 = new Resume();
        Resume r5 = new Resume();

        r5.setUuid("uuid5");
        r4.setUuid("uuid4");
        r3.setUuid("uuid3");
        r1.setUuid("uuid1");
        r2.setUuid("uuid2");

        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        r3.setUuid("nouuid3");
        System.out.println("\nUpdating uuid3:");
        ARRAY_STORAGE.update(r3);
        printAll();

        System.out.println("Deleting uuid1:");
        ARRAY_STORAGE.delete("uuid1");
        printAll();

        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
