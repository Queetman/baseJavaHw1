package ru.storage;

import org.junit.Assert;
import ru.Config;
import ru.model.Resume;

import java.util.List;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}
