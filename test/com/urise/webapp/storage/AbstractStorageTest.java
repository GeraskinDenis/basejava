package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractStorageTest {
    protected final AbstractStorage storage;
    protected final Resume RESUME_UUID_1 = new Resume("uuid1");
    protected final Resume RESUME_UUID_2 = new Resume("uuid2");
    protected final Resume RESUME_UUID_3 = new Resume("uuid3");

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
    public void testClear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void testDelete() {
        String uuid = RESUME_UUID_1.getUuid();
        storage.delete(uuid);
        Assert.assertNull(storage.get(uuid));
    }

    @Test
    public void testGet() {
        String uuid = RESUME_UUID_1.getUuid();
        Assert.assertEquals(storage.get(uuid).getUuid(), uuid);
    }

    @Test
    public void testGetAll() {
        Resume[] actuals = storage.getAll();
        Resume[] expecteds = new Resume[]{RESUME_UUID_1, RESUME_UUID_2, RESUME_UUID_3};
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testSave() {
        String uuid4 = "uuid4";
        Resume expected = new Resume(uuid4);
        storage.save(expected);
        Assert.assertSame(expected, storage.get(uuid4));
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
}