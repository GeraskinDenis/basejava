package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.resume.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest<AbstractArrayStorage> {

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        int i = storage.size();
        while (true) {
            storage.save(new Resume("RESUME_UUID_" + ++i));
        }
    }

    @Test
    public void getIndexNotExistUUID() {
        Integer expected = storage instanceof SortedArrayStorage ? -5 : -1;
        Assert.assertEquals(expected, storage.getIndex(UUID_NOT_EXIST));
    }

    protected void assertArrayEquals(Resume[] expecteds) {
        Resume[] actuals = storage.getAll();
        Assert.assertArrayEquals(expecteds, actuals);
        assertSize(expecteds.length);
    }

    @Test
    public abstract void deleteFirst();

    @Test
    public abstract void deleteMiddle();

    @Test
    public abstract void deleteLast();

    @Test
    public abstract void getIndex();

    @Test
    public abstract void saveAtFirst();

    @Test
    public abstract void saveAtMiddle();

    @Test
    public abstract void saveAtEnd();
}