package ru.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.exception.StorageException;
import ru.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() throws Exception {
        //Проверка на заполнение
        try {
            for (int i = storage.size() + 1; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException ex) {
            Assert.fail();
        }
        //Проверка на переполнение
        storage.save(new Resume());
    }
}
