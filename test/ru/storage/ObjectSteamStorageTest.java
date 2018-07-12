package ru.storage;

public class ObjectSteamStorageTest extends AbstractStorageTest {
    public ObjectSteamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
