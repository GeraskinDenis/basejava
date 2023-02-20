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
    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_UUID_1 = new Resume(UUID_1);
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_UUID_3 = new Resume(UUID_3);
    protected static final String UUID_5 = "uuid5";
    protected static final Resume RESUME_UUID_5 = new Resume(UUID_5);
    protected static final String UUID_NOT_EXIST = "uuidNotExist";

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID_1);
        storage.save(RESUME_UUID_3);
        storage.save(RESUME_UUID_5);
    }

    @Test
    public void clear() {
        storage.clear();
        assertGetAll(new Resume[0]);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertGetAll(new Resume[]{RESUME_UUID_3, RESUME_UUID_5});
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() {
        assertGet(RESUME_UUID_1);
        assertGet(RESUME_UUID_3);
        assertGet(RESUME_UUID_5);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        assertGetAll(new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5});
    }

    @Test
    public void save() {
        String uuid4 = "uuid4";
        Resume expected = new Resume(uuid4);
        storage.save(expected);
        assertSize(4);
        assertGet(expected);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_UUID_1);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void update() {
        Resume expected = new Resume(UUID_1);
        storage.update(expected);
        Assert.assertSame(expected, storage.get(UUID_1));
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    protected void assertSize(int expected) {
        Assert.assertEquals("The number of elements in storage is different!", expected, storage.size());
    }

    private void assertGet(Resume expected) {
        Assert.assertSame(expected.toString(), expected, storage.get(expected.getUuid()));
    }

    private void assertGetAll(Resume[] resumes) {
        List<Resume> actuals = Arrays.asList(storage.getAll());
        List<Resume> expecteds = Arrays.asList(resumes);
        Assert.assertEquals(expecteds.size(), actuals.size());
        Assert.assertTrue(actuals.containsAll(expecteds));
        Assert.assertTrue(expecteds.containsAll(actuals));
    }
}