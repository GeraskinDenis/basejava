package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected final AbstractStorage storage;
    protected final Resume RESUME_UUID_1 = new Resume("uuid1");
    protected final Resume RESUME_UUID_2 = new Resume("uuid2");
    protected final Resume RESUME_UUID_3 = new Resume("uuid3");
    protected static final String UUID_NOT_EXIST = "uuidNotExist";

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID_1);
        storage.save(RESUME_UUID_2);
        storage.save(RESUME_UUID_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void delete() {
        String uuid = RESUME_UUID_1.getUuid();
        storage.delete(uuid);
        Assert.assertNull(storage.get(uuid));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() {
        String uuid = RESUME_UUID_1.getUuid();
        Assert.assertEquals(uuid, storage.get(uuid).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Resume> actuals = Arrays.asList(storage.getAll());
        List<Resume> expecteds = Arrays.asList(RESUME_UUID_1, RESUME_UUID_2, RESUME_UUID_3);
        Assert.assertEquals(expecteds.size(), actuals.size());
        Assert.assertTrue(actuals.containsAll(expecteds));
        Assert.assertTrue(expecteds.containsAll(actuals));
    }

    @Test
    public void testSave() {
        String uuid4 = "uuid4";
        Resume expected = new Resume(uuid4);
        storage.save(expected);
        Assert.assertSame(expected, storage.get(uuid4));
    }

    @Test(expected = ExistStorageException.class)
    public void testSaveExist() {
        storage.save(RESUME_UUID_1);
    }

    @Test
    public void testSize() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void testUpdate() {
        String uuid1 = "uuid1";
        Resume expected = new Resume(uuid1);
        storage.update(expected);
        Assert.assertSame(expected, storage.get(uuid1));
    }

    @Test(expected = NotExistStorageException.class)
    public void testUpdateNotExist() {
        storage.update(new Resume(UUID_NOT_EXIST));
    }
}