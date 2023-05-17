package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.resume.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest<T extends Storage> {
    protected final T storage;
    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_UUID_1 = new Resume(UUID_1, "FullName1");
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_UUID_3 = new Resume(UUID_3, "FullName3");
    protected static final String UUID_5 = "uuid5";
    protected static final Resume RESUME_UUID_5 = new Resume(UUID_5, "FullName5");

    protected static final String UUID_7 = "uuid7";
    protected static final Resume RESUME_UUID_7 = new Resume(UUID_7, "FullName3");
    protected static final String UUID_NOT_EXIST = "uuidNotExist";

    public AbstractStorageTest(T storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID_1);
        storage.save(RESUME_UUID_3);
        storage.save(RESUME_UUID_5);
        storage.save(RESUME_UUID_7);
    }

    @Test
    public void clear() {
        storage.clear();
        assertGetAll(new Resume[0]);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertGetAll(new Resume[]{RESUME_UUID_3, RESUME_UUID_5, RESUME_UUID_7});
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
        assertGet(RESUME_UUID_7);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        assertGetAllSorted(new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_7, RESUME_UUID_5});
    }

    @Test
    public void save() {
        Resume expected = new Resume("uuid4", "FullName4");
        storage.save(expected);
        assertSize(5);
        assertGet(expected);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_UUID_1);
    }

    @Test
    public void size() {
        assertSize(4);
    }

    @Test
    public void update() {
        Resume expected = new Resume(UUID_1, "newFullName");
        storage.update(expected);
        Assert.assertSame(expected, storage.get(UUID_1));
        assertSize(4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_NOT_EXIST, "TestName"));
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

    private void assertGetAllSorted(Resume[] expecteds) {
        Resume[] actuals = storage.getAllSorted().toArray(new Resume[0]);
        Assert.assertEquals(expecteds.length, actuals.length);
        Assert.assertArrayEquals(expecteds, actuals);
    }
}