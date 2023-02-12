package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    protected final AbstractArrayStorage storage;
    protected static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_UUID_1 = new Resume(UUID_1);
    protected static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_UUID_3 = new Resume(UUID_3);
    protected static final String UUID_5 = "uuid5";
    protected static final Resume RESUME_UUID_5 = new Resume(UUID_5);
    protected static final String UUID_NOT_EXIST = "uuidNotExist";

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        this.storage = storage;
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertArrayEquals(new Resume[10_000], storage.storage);
        assertSize(0);
    }

    @Before
    public void setUp() {
        Arrays.fill(storage.storage, null);
        storage.storage[0] = RESUME_UUID_1;
        storage.storage[1] = RESUME_UUID_3;
        storage.storage[2] = RESUME_UUID_5;
        storage.size = 3;
    }

    @Test
    public abstract void deleteFirst();

    @Test
    public abstract void deleteMiddle();

    @Test
    public abstract void deleteLast();

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    protected void assertDelete(Resume[] expecteds) {
        Resume[] actuals = Arrays.copyOf(storage.storage, 2);
        Assert.assertArrayEquals(expecteds, actuals);
        assertSize(expecteds.length);
    }

    @Test
    public void get() {
        for (int i = 0; i < storage.size; i++) {
            assertGet(storage.storage[i]);
        }
    }

    protected void assertGet(Resume expected) {
        Assert.assertSame(expected.toString(), expected, storage.get(expected.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] expected = getResumes();
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void save() {
        Resume newResume = new Resume("uuid4");
        storage.save(newResume);
        assertGet(newResume);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_UUID_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        int i = storage.storage.length;
        while (true) {
            storage.save(new Resume("RESUME_UUID_" + ++i));
        }
    }

    @Test
    public void size() {
        assertSize(3);
    }

    protected void assertSize(int size) {
        Assert.assertEquals("The number of elements in storage is different!", size, storage.size());
    }

    @Test
    public void update() {
        Resume newResumeUUID1 = new Resume(UUID_1);
        storage.update(newResumeUUID1);
        Assert.assertSame(newResumeUUID1, storage.storage[0]);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(UUID_NOT_EXIST));
    }

    @Test
    public void getIndex() {
        Resume[] resumes = getResumes();
        for (int index = 0; index < resumes.length; index++) {
            assertGetIndex(index, resumes[index].getUuid());
        }
    }

    private void assertGetIndex(int expected, String UUID) {
        Assert.assertEquals(expected, storage.getIndex(UUID));
    }

    @Test
    public void getIndexNotExistUUID() {
        int expected = storage instanceof SortedArrayStorage ? -4 : -1;
        Assert.assertEquals(expected, storage.getIndex(UUID_NOT_EXIST));
    }

    protected Resume[] getResumes() {
        return new Resume[]{RESUME_UUID_1, RESUME_UUID_3, RESUME_UUID_5};
    }
}