package ru.storage;

import org.junit.Assert;
import ru.Config;
import ru.model.Resume;

import java.util.List;

public class SqlStorageTest extends AbstractStorageTest {
    public SqlStorageTest() {
        super(Config.get().getStorage());
    }

    @Override
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();

        Assert.assertEquals(list.get(0).getUuid(), resume1.getUuid()+"                               ");
        Assert.assertEquals(list.get(1).getUuid(), resume2.getUuid()+"                               ");
        Assert.assertEquals(list.get(2).getUuid(), resume3.getUuid()+"                               ");
    }
}
