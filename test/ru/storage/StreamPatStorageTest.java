package ru.storage;

import ru.storage.serializer.DataStreamSerializer;


public class StreamPatStorageTest extends AbstractStorageTest {

    public StreamPatStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
