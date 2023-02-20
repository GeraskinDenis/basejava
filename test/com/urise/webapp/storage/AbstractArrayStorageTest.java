package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(AbstractStorage storage) {
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
    public void getIndex() {
        Resume[] resumes = storage.getAll();
        for (int index = 0; index < resumes.length; index++) {
            assertGetIndex(index, resumes[index].getUuid());
        }
    }

    @Test
    public void getIndexNotExistUUID() {
        int expected = storage instanceof SortedArrayStorage ? -4 : -1;
        Assert.assertEquals(expected, ((AbstractArrayStorage) storage).getIndex(UUID_NOT_EXIST));
    }

    protected void assertArrayEquals(Resume[] expecteds) {
        Resume[] actuals = storage.getAll();
        Assert.assertArrayEquals(expecteds, actuals);
        assertSize(expecteds.length);
    }

    private void assertGetIndex(int expected, String UUID) {
        Assert.assertEquals(expected, ((AbstractArrayStorage) storage).getIndex(UUID));
    }

    @Test
    public abstract void deleteFirst();

    @Test
    public abstract void deleteMiddle();

    @Test
    public abstract void deleteLast();

    @Test
    public abstract void saveAtFirst();

    @Test
    public abstract void saveAtMiddle();

    @Test
    public abstract void saveAtEnd();
}